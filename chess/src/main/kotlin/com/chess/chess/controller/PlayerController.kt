package com.chess.chess.controller

import com.chess.chess.dto.PlayerDto
import com.chess.chess.service.PlayerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/players")
class PlayerController @Autowired constructor(
    private val playerService: PlayerService
) {
    @GetMapping("/{id}")
    fun getPlayer(@PathVariable id: Long): ResponseEntity<PlayerDto> {
        val playerDto = playerService.getPlayerById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(playerDto)
    }

    @PostMapping
    fun createPlayer(@RequestBody playerDto: PlayerDto): ResponseEntity<PlayerDto> {
        val createdPlayer = playerService.createPlayer(playerDto)
        return ResponseEntity.ok(createdPlayer)
    }

    @PutMapping("/{id}")
    fun updatePlayer(@PathVariable id: Long, @RequestBody playerDto: PlayerDto): ResponseEntity<PlayerDto> {
        val updatedPlayer = playerService.updatePlayer(id, playerDto) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(updatedPlayer)
    }

    @DeleteMapping("/{id}")
    fun deletePlayer(@PathVariable id: Long): ResponseEntity<Void> {
        playerService.deletePlayer(id)
        return ResponseEntity.noContent().build()
    }

    @PutMapping("/{id}/enter-queue")
    fun enterQueue(@PathVariable id: Long): ResponseEntity<Void> {
        return if (playerService.enterQueue(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/{id}/leave-queue")
    fun leaveQueue(@PathVariable id: Long): ResponseEntity<Void> {
        return if (playerService.leaveQueue(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/in-queue")
    fun getPlayersInQueue(): ResponseEntity<List<PlayerDto>> {
        val playersInQueue = playerService.getPlayersInQueue()
        return ResponseEntity.ok(playersInQueue)
    }
}
