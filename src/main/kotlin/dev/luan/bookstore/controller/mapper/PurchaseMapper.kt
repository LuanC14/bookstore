package dev.luan.bookstore.controller.mapper

import dev.luan.bookstore.controller.request.PostPurchaseRequest
import dev.luan.bookstore.entity.PurchaseEntity
import dev.luan.bookstore.service.BookService
import dev.luan.bookstore.service.CustomerService
import org.springframework.stereotype.Component

@Component
class PurchaseMapper(
    private val bookService: BookService,
    private val customerService: CustomerService
) {

     fun toEntity(request: PostPurchaseRequest): PurchaseEntity {
        val customer = customerService.findById(request.customerId)
        val books = bookService.findAllByIds(request.bookIds)

        return PurchaseEntity(
            customer = customer,
            books = books.toMutableList(),
            price = books.sumOf { it.price }
        )
    }
}