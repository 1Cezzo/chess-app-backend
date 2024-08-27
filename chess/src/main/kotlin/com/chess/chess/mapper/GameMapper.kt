package com.chess.chess.mapper

import com.chess.chess.model.Game
import com.chess.chess.model.Player
import com.chess.chess.dto.GameDto
import com.chess.chess.mapper.PlayerMapper

object GameMapper {
    fun toDto(game: Game): GameDto = GameDto(
        id = game.id,
        player1Id = game.player1.id!!,
        player2Id = game.player2.id!!,
        result = game.result,
        moves = game.moves,
        fen = game.fen
    )

    fun toEntity(gameDto: GameDto): Game = Game(
        id = gameDto.id,
        player1 = Player(id = gameDto.player1Id, username = "", email = "", rating = 0, inQueue = false),
        player2 = Player(id = gameDto.player2Id, username = "", email = "", rating = 0, inQueue = false),
        result = gameDto.result,
        moves = gameDto.moves,
        fen = gameDto.fen
    )
}
