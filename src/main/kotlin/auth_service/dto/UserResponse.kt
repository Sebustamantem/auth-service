package auth_service.dto

data class UserResponse(
    val id: Long?,
    val name: String,
    val email: String
)
