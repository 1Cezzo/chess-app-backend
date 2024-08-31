package com.chess.chess.service

import com.chess.chess.dto.PlayerDto
import com.chess.chess.model.Player
import com.chess.chess.repository.PlayerRepository
import com.chess.chess.mapper.PlayerMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus

@Service
class PlayerService @Autowired constructor(
    private val playerRepository: PlayerRepository
) {
    fun getPlayerById(id: Long): PlayerDto? {
        return playerRepository.findById(id).orElse(null)?.let { PlayerMapper.toDto(it) }
    }

    fun createPlayer(playerDto: PlayerDto): PlayerDto {
        if (playerRepository.existsByUsername(playerDto.username)) {
            throw ResponseStatusException(HttpStatus.CONFLICT, "Username already exists")
        }

        if (playerRepository.existsByEmail(playerDto.email)) {
            throw ResponseStatusException(HttpStatus.CONFLICT, "Email already exists")
        }

        val player = PlayerMapper.toEntity(playerDto)
        val savedPlayer = playerRepository.save(player)
        return PlayerMapper.toDto(savedPlayer)
    }

    fun getPlayers(): List<PlayerDto> {
        val players = playerRepository.findAll() ?: emptyList()
        return players.map { PlayerMapper.toDto(it) }
    }

    fun updatePlayer(id: Long, playerDto: PlayerDto): PlayerDto? {
        val existingPlayer = playerRepository.findById(id).orElse(null) ?: return null
        val updatedPlayer = existingPlayer.copy(
            username = playerDto.username,
            email = playerDto.email,
            rating = playerDto.rating
        )
        val savedPlayer = playerRepository.save(updatedPlayer)
        return PlayerMapper.toDto(savedPlayer)
    }

    fun deletePlayer(id: Long) {
        playerRepository.deleteById(id)
    }

    fun enterQueue(id: Long): Boolean {
        val player = playerRepository.findById(id).orElse(null)
        return if (player != null) {
            player.inQueue = true
            playerRepository.save(player)
            true
        } else {
            false
        }
    }

    fun leaveQueue(id: Long): Boolean {
        val player = playerRepository.findById(id).orElse(null)
        return if (player != null) {
            player.inQueue = false
            playerRepository.save(player)
            true
        } else {
            false
        }
    }

    fun getPlayersInQueue(): List<PlayerDto> {
        val playersInQueue = playerRepository.findByInQueue(true) ?: emptyList()
        return playersInQueue.map { PlayerMapper.toDto(it) }
    }

    fun login(username: String, email: String): PlayerDto? {
        val player = playerRepository.findByUsername(username) ?: return null
        return if (player.email == email) {
            PlayerMapper.toDto(player)
        } else {
            null
        }
    }
}
