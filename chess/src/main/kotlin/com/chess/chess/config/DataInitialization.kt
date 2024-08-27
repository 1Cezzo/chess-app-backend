package com.chess.chess.config

import com.chess.chess.model.Player
import com.chess.chess.repository.PlayerRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DataInitialization {

    @Bean
    fun init(playerRepository: PlayerRepository) = CommandLineRunner {
        playerRepository.save(Player(username = "player1", email = "player1@example.com", rating = 1200, inQueue = false))
        playerRepository.save(Player(username = "player2", email = "player2@example.com", rating = 1200, inQueue = false))
    }
}
