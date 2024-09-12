package dev.luan.bookstore.event.listener

import dev.luan.bookstore.event.PurchaseEvent
import dev.luan.bookstore.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class GenerateNfeListener(
    private val purchaseService: PurchaseService
) {

    @EventListener
    fun listen(purchaseEvent: PurchaseEvent) {
        val nfe = UUID.randomUUID().toString();
        val purchaseEntity = purchaseEvent.purchaseEntity.copy(nfe = nfe)
        purchaseService.update(purchaseEntity);
    }
}