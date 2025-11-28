package auth_service.model

import jakarta.persistence.*

@Entity
@Table(name = "Usuarios")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,
    val lastName: String,
    val email: String,
    val password: String,
    val phone: String
)
