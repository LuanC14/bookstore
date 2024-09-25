package dev.luan.bookstore.service

import dev.luan.bookstore.event.PurchaseEvent
import dev.luan.bookstore.helper.buildPurchase
import dev.luan.bookstore.repository.PurchaseRepository
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationEventPublisher
import kotlin.test.assertEquals

class PurchaseServiceTest {

    @MockK
    private lateinit var purchaseRepository: PurchaseRepository

    @MockK
    private lateinit var applicationEventPublisher: ApplicationEventPublisher

    @InjectMockKs
    private lateinit var purchaseService: PurchaseService

    private val purchaseEventSlot = slot<PurchaseEvent>()

    @Test
    fun `should create purchase and publish event`() {
        val purchase = buildPurchase()

        every { purchaseRepository.save(purchase) } returns purchase

        every { applicationEventPublisher.publishEvent(any()) } just runs

        purchaseService.buy(purchase)

        verify(exactly = 1) { purchaseRepository.save(purchase) }
        verify(exactly = 1) { applicationEventPublisher.publishEvent(capture(purchaseEventSlot)) }

        assertEquals(purchase, purchaseEventSlot.captured.purchaseEntity)
    }

    @Test
    fun `should update purchase`() {
        val purchase = buildPurchase()

        every { purchaseRepository.save(purchase) } returns purchase

        purchaseService.update(purchase)

        verify(exactly = 1) { purchaseRepository.save(purchase) }
    }
}