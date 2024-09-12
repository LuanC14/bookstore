package dev.luan.bookstore.event.listener

import dev.luan.bookstore.event.PurchaseEvent
import dev.luan.bookstore.service.BookService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class UpdateSoldBookListener(
    private val bookService: BookService
) {

    @EventListener
    fun listen(purchaseEvent: PurchaseEvent) {
        bookService.purchase(purchaseEvent.purchaseEntity.books)
    }
}