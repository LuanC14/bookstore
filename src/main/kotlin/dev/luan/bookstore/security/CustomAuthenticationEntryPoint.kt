package dev.luan.bookstore.security

import com.fasterxml.jackson.databind.ObjectMapper
import dev.luan.bookstore.controller.response.error.ErrorResponse
import dev.luan.bookstore.enum.Errors
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationEntryPoint(
    private val mapper: ObjectMapper
) : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        response?.contentType = "application/json"
        response?.status = HttpServletResponse.SC_UNAUTHORIZED

        val errorResponse = ErrorResponse(
            HttpStatus.UNAUTHORIZED.value(),
            Errors.BS000.message,
            Errors.BS000.code,
            null
        )

        response?.outputStream?.print(mapper.writeValueAsString(errorResponse))
    }
}