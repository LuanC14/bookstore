package dev.luan.bookstore.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import dev.luan.bookstore.entity.CustomerEntity
import java.time.Instant

fun generateToken(customer: CustomerEntity, secretKey: String, expiresIn: Instant): String {
    try {
        val algorithm = Algorithm.HMAC256(secretKey)

        val roles: MutableList<String> = customer.roles
            .map { it.name }
            .toMutableList()

        return JWT.create()
            .withIssuer("bookstore")
            .withSubject(customer.id.toString())
            .withClaim("roles", roles)
            .withExpiresAt(expiresIn)
            .sign(algorithm)
    } catch (e: Exception) {
        throw RuntimeException("Token generation failed")
    }
}

fun validateJwtToken(secretKey: String, token: String): DecodedJWT? {
    val normalizedToken = token.replace("Bearer ", "")

    val algorithm: Algorithm = Algorithm.HMAC256(secretKey)

    try {
        val tokenDecoded = JWT.require(algorithm)
            .build()
            .verify(normalizedToken)

        return tokenDecoded
    } catch (e: JWTVerificationException) {
        e.printStackTrace()
        return null
    }
}