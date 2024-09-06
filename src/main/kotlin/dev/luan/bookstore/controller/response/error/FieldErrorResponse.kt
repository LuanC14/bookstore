package dev.luan.bookstore.controller.response.error

data class FieldErrorResponse(
    val message: String,
    val field: String
)