package com.chess.chess.repository

import com.chess.chess.model.Player
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlayerRepository : JpaRepository<Player, Long> {
    fun findByUsername(username: String): Player?
    fun findByEmail(email: String): Player?
    fun findByInQueue(inQueue: Boolean): List<Player>?
}
