package dev.luan.bookstore.controller

import dev.luan.bookstore.controller.request.AuthRequest
import dev.luan.bookstore.controller.response.AuthResponse
import dev.luan.bookstore.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    val authService: AuthService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun auth(@RequestBody request: AuthRequest): AuthResponse {
        return authService.authenticate(request)
    }
}