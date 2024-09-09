package dev.luan.bookstore.repository

import dev.luan.bookstore.entity.PurchaseEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PurchaseRepository : JpaRepository<PurchaseEntity, Int> {
}