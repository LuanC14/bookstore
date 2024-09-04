package dev.luan.bookstore.controller.response

import dev.luan.bookstore.enums.BookStatus
import dev.luan.bookstore.model.CustomerModel
import java.math.BigDecimal

data class BookResponse(
    var id: Int? = null,

    var name: String,

    var price: BigDecimal,

    var customer: CustomerModel? = null,

    var status: BookStatus? = null
)