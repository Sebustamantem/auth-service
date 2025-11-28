package auth_service.dto

data class AuthResponse(
    val id: Long?,
    val name: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val token: String
)




