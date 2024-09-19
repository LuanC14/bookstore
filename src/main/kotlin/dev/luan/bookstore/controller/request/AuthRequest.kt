package dev.luan.bookstore.controller.request

data class AuthRequest(
    val email: String,
    val password: String
) {
}