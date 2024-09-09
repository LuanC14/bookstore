package dev.luan.bookstore.extension

import dev.luan.bookstore.controller.request.PostBookRequest
import dev.luan.bookstore.controller.request.PostCustomerRequest
import dev.luan.bookstore.controller.request.PutBookRequest
import dev.luan.bookstore.controller.request.PutCustomerRequest
import dev.luan.bookstore.controller.response.BookResponse
import dev.luan.bookstore.controller.response.CustomerResponse
import dev.luan.bookstore.enum.BookStatus
import dev.luan.bookstore.enum.CustomerStatus
import dev.luan.bookstore.entity.BookEntity
import dev.luan.bookstore.entity.CustomerEntity

fun PostCustomerRequest.toEntity(): CustomerEntity {
    return CustomerEntity(name = this.name, email = this.email, status = CustomerStatus.ATIVO)
}

fun PutCustomerRequest.toEntity(previousValue: CustomerEntity): CustomerEntity {
    return CustomerEntity(id = previousValue.id, name = this.name, email = this.email, status = previousValue.status)
}

fun PostBookRequest.toEntity(customer: CustomerEntity): BookEntity {
    return BookEntity(
        name = this.name,
        price = this.price,
        status = BookStatus.ATIVO,
        customer = customer
    )
}

fun PutBookRequest.toEntity(previousValue: BookEntity): BookEntity {
    return BookEntity(
        id = previousValue.id,
        name = this.name ?: previousValue.name,
        price = this.price ?: previousValue.price,
        status = previousValue.status,
        customer = previousValue.customer
    )
}

fun CustomerEntity.toResponse(): CustomerResponse {
    return CustomerResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        status = this.status
    )
}

fun BookEntity.toResponse(): BookResponse {
    return BookResponse(
        id = this.id,
        name = this.name,
        price = this.price,
        customer = this.customer,
        status = this.status
    )
}