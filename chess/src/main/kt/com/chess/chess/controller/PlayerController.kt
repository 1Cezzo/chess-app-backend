package kt.com.chess.chess.controller

import kt.com.chess.chess.dto.PlayerDto
import kt.com.chess.chess.service.PlayerService
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
}
