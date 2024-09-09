package dev.luan.bookstore.controller.response

import dev.luan.bookstore.enum.BookStatus
import dev.luan.bookstore.entity.CustomerEntity
import java.math.BigDecimal

data class BookResponse(
    var id: Int? = null,

    var name: String,

    var price: BigDecimal,

    var customer: CustomerEntity? = null,

    var status: BookStatus? = null
)