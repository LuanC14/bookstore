package dev.luan.bookstore.extension

import dev.luan.bookstore.controller.mapper.PurchaseMapper
import dev.luan.bookstore.controller.request.*
import dev.luan.bookstore.controller.response.BookResponse
import dev.luan.bookstore.controller.response.CustomerResponse
import dev.luan.bookstore.controller.response.PageResponse
import dev.luan.bookstore.enum.BookStatus
import dev.luan.bookstore.enum.CustomerStatus
import dev.luan.bookstore.entity.BookEntity
import dev.luan.bookstore.entity.CustomerEntity
import dev.luan.bookstore.entity.PurchaseEntity
import org.springframework.data.domain.Page

fun PostCustomerRequest.toEntity(): CustomerEntity {
    return CustomerEntity(name = this.name, email = this.email, status = CustomerStatus.ATIVO, password = this.password)
}

fun PutCustomerRequest.toEntity(previousValue: CustomerEntity): CustomerEntity {
    return CustomerEntity(
        id = previousValue.id,
        name = this.name,
        email = this.email,
        status = previousValue.status,
        previousValue.password
    )
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

fun PostPurchaseRequest.toEntity(mapper: PurchaseMapper): PurchaseEntity {
    return mapper.toEntity(this)
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

fun <T> Page<T>.toPageResponse(): PageResponse<T> {
    return PageResponse(
        this.content,
        this.number,
        this.totalElements,
        this.totalPages
    )
}