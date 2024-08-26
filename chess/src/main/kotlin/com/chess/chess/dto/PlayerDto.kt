package kotlin.com.chess.chess.dto

data class PlayerDto(
    val id: Long? = null,
    val username: String,
    val email: String,
    val rating: Int = 1200
)
