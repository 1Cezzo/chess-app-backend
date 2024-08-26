package kt.com.chess.chess.controller

import kt.com.chess.chess.dto.GameDto
import kt.com.chess.chess.service.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/games")
class GameController @Autowired constructor(
    private val gameService: GameService
) {
    @GetMapping("/{id}")
    fun getGame(@PathVariable id: Long): ResponseEntity<GameDto> {
        val gameDto = gameService.getGameById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(gameDto)
    }

    @PostMapping
    fun createGame(@RequestBody gameDto: GameDto): ResponseEntity<GameDto> {
        val createdGame = gameService.createGame(gameDto)
        return ResponseEntity.ok(createdGame)
    }

    @PutMapping("/{id}")
    fun updateGame(@PathVariable id: Long, @RequestBody gameDto: GameDto): ResponseEntity<GameDto> {
        val updatedGame = gameService.updateGame(id, gameDto) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(updatedGame)
    }

    @DeleteMapping("/{id}")
    fun deleteGame(@PathVariable id: Long): ResponseEntity<Void> {
        gameService.deleteGame(id)
        return ResponseEntity.noContent().build()
    }
}
