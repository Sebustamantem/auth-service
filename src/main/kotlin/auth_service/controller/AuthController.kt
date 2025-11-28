package auth_service.controller

import auth_service.dto.*
import auth_service.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val userService: UserService
) {

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): AuthResponse {
        return userService.register(request)
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): AuthResponse {
        return userService.login(request)
    }
}
