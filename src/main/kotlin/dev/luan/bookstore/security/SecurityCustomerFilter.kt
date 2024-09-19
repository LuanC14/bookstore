package dev.luan.bookstore.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class SecurityCustomerFilter(
    @Value("\${environments.secret_key}")
    private val secretKey: String
) : OncePerRequestFilter() {

    override fun doFilterInternal(req: HttpServletRequest, res: HttpServletResponse, filterChain: FilterChain) {
        try {
            val authorizationHeader = req.getHeader("Authorization")

            if (authorizationHeader != null) {
                val token =
                    validateJwtToken(secretKey, authorizationHeader) ?: throw RuntimeException("Invalid Token")

                req.setAttribute("customer_id", token.subject)

                val roles = token.getClaim("roles").asList(String::class.java).toList()
                val grants = roles.map { SimpleGrantedAuthority(it) }

                val auth = UsernamePasswordAuthenticationToken(token.subject, null, grants)

                SecurityContextHolder.getContext().authentication = auth
            }

            filterChain.doFilter(req, res)

        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("Failed Authentication. ${e.localizedMessage}")
        }
    }

    fun validateJwtToken(secretKey: String, token: String): DecodedJWT? {
        var token = token
        token = token.replace("Bearer ", "")

        val algorithm: Algorithm = Algorithm.HMAC256(secretKey)

        try {
            val tokenDecoded = JWT.require(algorithm)
                .build()
                .verify(token)

            return tokenDecoded
        } catch (e: JWTVerificationException) {
            e.printStackTrace()
            return null
        }
    }
}