package auth_service.dto



data class RegisterRequest(
    val name: String,
    val lastName: String,
    val email: String,
    val password: String,
    val phone: String
)
