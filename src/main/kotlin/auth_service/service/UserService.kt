package auth_service.service

import auth_service.dto.*
import auth_service.model.User
import auth_service.repository.UserRepository
import auth_service.security.JwtService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import org.springframework.security.crypto.password.PasswordEncoder

@Service
class UserService(
    private val userRepository: UserRepository,
    private val jwtService: JwtService,
    private val passwordEncoder: PasswordEncoder
) {

    fun register(request: RegisterRequest): AuthResponse {

        val email = request.email.trim().lowercase()

        if (userRepository.existsByEmail(email)) {
            throw ResponseStatusException(HttpStatus.CONFLICT, "El usuario ya existe")
        }

        val user = User(
            name = request.name,
            lastName = request.lastName,
            email = email,
            phone = request.phone,
            password = passwordEncoder.encode(request.password)
        )

        val saved = userRepository.save(user)
        val token = jwtService.generateToken(saved.email)

        return AuthResponse(
            id = saved.id,
            name = saved.name,
            lastName = saved.lastName,
            email = saved.email,
            phone = saved.phone,
            token = token
        )
    }

    fun login(request: LoginRequest): AuthResponse {

        val email = request.email.trim().lowercase()

        val user = userRepository.findByEmail(email)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado")

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Contraseña incorrecta")
        }

        val token = jwtService.generateToken(user.email)

        return AuthResponse(
            id = user.id,
            name = user.name,
            lastName = user.lastName,
            email = user.email,
            phone = user.phone,
            token = token
        )
    }
}
