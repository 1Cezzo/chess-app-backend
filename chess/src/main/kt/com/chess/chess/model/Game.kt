package kt.com.chess.chess.model

import jakarta.persistence.*;

@Entity
data class Game(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    val player1: Player,

    @ManyToOne
    val player2: Player,

    val result: String = "",

    // List of moves in standard algebraic notation
    @ElementCollection
    var moves: List<String> = listOf(),

    // Current board state in FEN notation
    var fen: String = ""
)
