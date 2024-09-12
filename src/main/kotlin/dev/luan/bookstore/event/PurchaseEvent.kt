package dev.luan.bookstore.event

import dev.luan.bookstore.entity.PurchaseEntity
import org.springframework.context.ApplicationEvent

class PurchaseEvent(
    source: Any,
    val purchaseEntity: PurchaseEntity
) : ApplicationEvent(source) {
}