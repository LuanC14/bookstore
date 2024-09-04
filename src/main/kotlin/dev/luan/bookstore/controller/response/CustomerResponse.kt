package dev.luan.bookstore.controller.response

import dev.luan.bookstore.enums.CustomerStatus

data class CustomerResponse(
    var id: Int? = null,

    var name: String,

    var email: String,

    var status: CustomerStatus
)