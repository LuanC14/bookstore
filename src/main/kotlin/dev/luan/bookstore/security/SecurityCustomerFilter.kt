package dev.luan.bookstore.security

import dev.luan.bookstore.utils.validateJwtToken
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
}