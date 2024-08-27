package com.chess.chess.repository

import com.chess.chess.model.Game
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GameRepository : JpaRepository<Game, Long> {
    fun findByPlayer1Id(player1Id: Long): List<Game>
    fun findByPlayer2Id(player2Id: Long): List<Game>
}