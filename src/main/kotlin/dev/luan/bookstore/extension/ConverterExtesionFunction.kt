package dev.luan.bookstore.extension

import dev.luan.bookstore.controller.request.PostBookRequest
import dev.luan.bookstore.controller.request.PostCustomerRequest
import dev.luan.bookstore.controller.request.PutBookRequest
import dev.luan.bookstore.controller.request.PutCustomerRequest
import dev.luan.bookstore.controller.response.BookResponse
import dev.luan.bookstore.controller.response.CustomerResponse
import dev.luan.bookstore.enums.BookStatus
import dev.luan.bookstore.enums.CustomerStatus
import dev.luan.bookstore.model.BookModel
import dev.luan.bookstore.model.CustomerModel

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(name = this.name, email = this.email, status = CustomerStatus.ATIVO)
}

fun PutCustomerRequest.toCustomerModel(previousValue: CustomerModel): CustomerModel {
    return CustomerModel(id = previousValue.id, name = this.name, email = this.email, status = previousValue.status)
}

fun PostBookRequest.toBookModel(customer: CustomerModel): BookModel {
    return BookModel(
        name = this.name,
        price = this.price,
        status = BookStatus.ATIVO,
        customer = customer
    )
}

fun PutBookRequest.toBookModel(previousValue: BookModel): BookModel {
    return BookModel(
        id = previousValue.id,
        name = this.name ?: previousValue.name,
        price = this.price ?: previousValue.price,
        status = previousValue.status,
        customer = previousValue.customer
    )
}

fun CustomerModel.toResponse(): CustomerResponse {
    return CustomerResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        status = this.status
    )
}

fun BookModel.toResponse(): BookResponse {
    return BookResponse(
        id = this.id,
        name = this.name,
        price = this.price,
        customer = this.customer,
        status = this.status
    )
}