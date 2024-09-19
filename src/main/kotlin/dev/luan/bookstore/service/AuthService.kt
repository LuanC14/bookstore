package dev.luan.bookstore.service


import dev.luan.bookstore.controller.request.AuthRequest
import dev.luan.bookstore.controller.response.AuthResponse
import dev.luan.bookstore.enum.CustomerStatus
import dev.luan.bookstore.enum.Errors
import dev.luan.bookstore.exception.ForbbidenException
import dev.luan.bookstore.exception.NotFoundException
import dev.luan.bookstore.exception.UnauthorizedException
import dev.luan.bookstore.repository.CustomerRepository
import dev.luan.bookstore.utils.generateToken
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.Instant

@Service
class AuthService(
    private val customerRepository: CustomerRepository,
    private val bCrypt: BCryptPasswordEncoder,
    @Value("\${environments.secret_key}")
    private val secretKey: String
) {

    fun authenticate(request: AuthRequest): AuthResponse {
        val customer = customerRepository.findByEmail(request.email)
            ?: throw NotFoundException(Errors.BS401.message.format(request.email), Errors.BS401.code)

        val passwordMatches = bCrypt.matches(request.password, customer.password)

        if (!passwordMatches) {
            throw UnauthorizedException(Errors.BS001.message, Errors.BS001.code)
        }

        if (customer.status == CustomerStatus.INATIVO) {
            throw ForbbidenException(Errors.BS002.message, Errors.BS002.code)
        }

        val expiresIn = Instant.now().plus(Duration.ofDays(1))

        val token = generateToken(customer, secretKey, expiresIn)

        return AuthResponse(token, expiresIn.toEpochMilli())
    }
}