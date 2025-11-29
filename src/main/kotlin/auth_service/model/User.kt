package auth_service.model

import jakarta.persistence.*

@Entity
@Table(name = "usuarios")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val lastName: String,

    @Column(nullable = false, unique = true)  // 🔥 Evita duplicados en BD
    val email: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false)
    val phone: String
)
