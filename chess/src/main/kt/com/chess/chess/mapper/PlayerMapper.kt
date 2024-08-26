package kt.com.chess.chess.mapper

import kt.com.chess.chess.model.Player
import kt.com.chess.chess.dto.PlayerDto

object PlayerMapper {
    fun toDto(player: Player): PlayerDto = PlayerDto(
        id = player.id,
        username = player.username,
        email = player.email,
        rating = player.rating
    )

    fun toEntity(playerDto: PlayerDto): Player = Player(
        id = playerDto.id,
        username = playerDto.username,
        email = playerDto.email,
        rating = playerDto.rating
    )
}
