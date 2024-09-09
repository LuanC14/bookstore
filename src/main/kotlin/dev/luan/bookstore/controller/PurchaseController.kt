package dev.luan.bookstore.controller

import dev.luan.bookstore.controller.request.PostPurchaseRequest
import dev.luan.bookstore.service.PurchaseService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController("/purchase")
class PurchaseController(
    private val purchaseService: PurchaseService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun purchase(@RequestBody @Valid request: PostPurchaseRequest) {

    }
}