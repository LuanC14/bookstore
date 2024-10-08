package dev.luan.bookstore.controller

import dev.luan.bookstore.controller.mapper.PurchaseMapper
import dev.luan.bookstore.controller.request.PostPurchaseRequest
import dev.luan.bookstore.extension.toEntity
import dev.luan.bookstore.service.PurchaseService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/purchase")
class PurchaseController(
    private val purchaseService: PurchaseService,
    private val purchaseMapper: PurchaseMapper
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun purchase(@RequestBody @Valid request: PostPurchaseRequest) {
        purchaseService.buy(request.toEntity(purchaseMapper))
    }
}