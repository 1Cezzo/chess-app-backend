package com.chess.chess.service

import com.chess.chess.dto.GameDto
import com.chess.chess.model.Game
import com.chess.chess.mapper.GameMapper
import com.chess.chess.repository.GameRepository
import com.chess.chess.repository.PlayerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GameService @Autowired constructor(
    private val gameRepository: GameRepository,
    private val playerRepository: PlayerRepository
) {
    fun getGame(id: Long): GameDto? {
        return gameRepository.findById(id).orElse(null)?.let { GameMapper.toDto(it) }
    }

    fun createGame(gameDto: GameDto): GameDto {
        val player1 = playerRepository.findById(gameDto.player1Id).orElseThrow { IllegalArgumentException("Player1 not found") }
        val player2 = playerRepository.findById(gameDto.player2Id).orElseThrow { IllegalArgumentException("Player2 not found") }

        val game = Game(
            player1 = player1,
            player2 = player2,
            result = gameDto.result,
            moves = gameDto.moves,
            fen = gameDto.fen
        )
        val savedGame = gameRepository.save(game)
        return GameMapper.toDto(savedGame)
    }

    fun getGameById(id: Long): GameDto? {
        return gameRepository.findById(id).orElse(null)?.let { GameMapper.toDto(it) }
    }

    fun updateGame(id: Long, gameDto: GameDto): GameDto? {
        val existingGame = gameRepository.findById(id).orElse(null) ?: return null
        val player1 = playerRepository.findById(gameDto.player1Id).orElseThrow { IllegalArgumentException("Player1 not found") }
        val player2 = playerRepository.findById(gameDto.player2Id).orElseThrow { IllegalArgumentException("Player2 not found") }

        val updatedGame = existingGame.copy(
            player1 = player1,
            player2 = player2,
            result = gameDto.result,
            moves = gameDto.moves,
            fen = gameDto.fen
        )
        val savedGame = gameRepository.save(updatedGame)
        return GameMapper.toDto(savedGame)
    }

    fun deleteGame(id: Long) {
        gameRepository.deleteById(id)
    }
}
