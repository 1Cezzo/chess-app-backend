package kt.com.chess.chess.service

import kt.com.chess.chess.dto.PlayerDto
import kt.com.chess.chess.model.Player
import kt.com.chess.chess.repository.PlayerRepository
import kt.com.chess.chess.mapper.PlayerMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PlayerService @Autowired constructor(
    private val playerRepository: PlayerRepository
) {
    fun getPlayerById(id: Long): PlayerDto? {
        return playerRepository.findById(id).orElse(null)?.let { PlayerMapper.toDto(it) }
    }

    fun createPlayer(playerDto: PlayerDto): PlayerDto {
        val player = PlayerMapper.toEntity(playerDto)
        val savedPlayer = playerRepository.save(player)
        return PlayerMapper.toDto(savedPlayer)
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
}
