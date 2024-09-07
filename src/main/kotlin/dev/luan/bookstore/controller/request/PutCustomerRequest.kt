package dev.luan.bookstore.controller.request

import dev.luan.bookstore.annotations.validations.EmailAvaiable
import jakarta.validation.constraints.NotEmpty

data class PutCustomerRequest (
    @field:NotEmpty(message = "O nome deve ser informado")
    var name: String,

    @EmailAvaiable
    var email: String
)