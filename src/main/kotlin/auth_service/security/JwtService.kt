package auth_service.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService {

    private val secret = "ESTE_ES_MI_SECRETO_SUPER_SEGURO_123456789"

    fun generateToken(email: String): String {
        val now = Date()
        val expiry = Date(now.time + 1000 * 60 * 60) // 1 hora

        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(now)
            .setExpiration(expiry)
            .signWith(SignatureAlgorithm.HS256, secret.toByteArray())
            .compact()
    }
}
