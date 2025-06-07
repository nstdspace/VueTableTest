import java.time.Instant
import java.util.UUID

data class ClientDto(
    val id: UUID,
    val firstName: String?,
    val lastName: String?,
    val company: String?,
    val email: String,
    val createdAt: Instant,
    val city: String,
    val zip: String,
)
