package dev.luan.bookstore.controller.request

import dev.luan.bookstore.annotation.validations.EmailAvaiable
import jakarta.validation.constraints.NotEmpty

data class PostCustomerRequest(

    @field:NotEmpty(message = "O nome deve ser informado.")
    var name: String,

    @EmailAvaiable
    var email: String
)