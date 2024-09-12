package dev.luan.bookstore.service

import dev.luan.bookstore.entity.PurchaseEntity
import dev.luan.bookstore.event.PurchaseEvent
import dev.luan.bookstore.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
    private val repository: PurchaseRepository,
    private val applicationEvenPublisher: ApplicationEventPublisher
) {

    fun create(entity: PurchaseEntity) {
        repository.save(entity)
        applicationEvenPublisher.publishEvent(PurchaseEvent(this, entity))
    }

    fun update(purchaseEntity: PurchaseEntity) {
        repository.save(purchaseEntity)
    }
}
