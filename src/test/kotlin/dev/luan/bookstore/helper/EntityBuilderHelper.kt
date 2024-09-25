package dev.luan.bookstore.helper

import dev.luan.bookstore.entity.BookEntity
import dev.luan.bookstore.entity.CustomerEntity
import dev.luan.bookstore.entity.PurchaseEntity
import dev.luan.bookstore.enum.CustomerStatus
import dev.luan.bookstore.enum.ProfileRoles
import java.math.BigDecimal
import java.util.*

fun buildCustomer(
    id: Int? = null,
    name: String = "customer name",
    email: String = "${UUID.randomUUID()}@email.com",
    password: String = "password"
) = CustomerEntity(
    id = id,
    name = name,
    email = email,
    status = CustomerStatus.ATIVO,
    password = password,
    roles = setOf(ProfileRoles.CUSTOMER)
)

fun buildPurchase(
    id: Int? = null,
    customer: CustomerEntity = buildCustomer(),
    books: MutableList<BookEntity> = mutableListOf(),
    nfe: String? = UUID.randomUUID().toString(),
    price: BigDecimal = BigDecimal.TEN
) = PurchaseEntity (
    id = id,
    customer = customer,
    books = books,
    nfe = nfe,
    price = price
)