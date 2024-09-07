package dev.luan.bookstore.controller.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class PutCustomerRequest (
    @field:NotEmpty(message = "O nome deve ser informado")
    var name: String,

    @field:Email(message = "O email deve ser válido")
    var email: String
)