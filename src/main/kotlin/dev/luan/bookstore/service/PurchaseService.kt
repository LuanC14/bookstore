package dev.luan.bookstore.service

import dev.luan.bookstore.entity.PurchaseEntity
import dev.luan.bookstore.enum.BookStatus
import dev.luan.bookstore.enum.Errors
import dev.luan.bookstore.event.PurchaseEvent
import dev.luan.bookstore.exception.BadRequestException
import dev.luan.bookstore.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
    private val repository: PurchaseRepository,
    private val applicationEvenPublisher: ApplicationEventPublisher
) {

    fun buy(entity: PurchaseEntity) {

        val notAvailableBooks: MutableList<String> = mutableListOf();

        entity.books.forEach {
            if (it.status == BookStatus.DELETADO || it.status == BookStatus.VENDIDO) {
                notAvailableBooks.add(it.name)
            }
        }

        if (notAvailableBooks.isNotEmpty()) {
            val booksNames = notAvailableBooks.joinToString(separator = ", ")
            throw BadRequestException(Errors.BS303.message.format(booksNames), Errors.BS303.code)
        }

        repository.save(entity)
        applicationEvenPublisher.publishEvent(PurchaseEvent(this, entity))
    }

    fun update(purchaseEntity: PurchaseEntity) {
        repository.save(purchaseEntity)
    }
}
