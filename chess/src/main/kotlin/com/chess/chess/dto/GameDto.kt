package com.chess.chess.dto

data class GameDto(
    val id: Long? = null,
    val player1Id: Long,
    val player2Id: Long,
    val result: String = "",
    val moves: List<String> = listOf(),
    val fen: String = ""
)
