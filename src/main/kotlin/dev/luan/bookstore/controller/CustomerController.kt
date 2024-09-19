package dev.luan.bookstore.controller

import dev.luan.bookstore.annotation.AccessOnlyForYourselfOrAdmin
import dev.luan.bookstore.controller.request.PostCustomerRequest
import dev.luan.bookstore.controller.request.PutCustomerRequest
import dev.luan.bookstore.controller.response.CustomerResponse
import dev.luan.bookstore.extension.toEntity
import dev.luan.bookstore.extension.toResponse
import dev.luan.bookstore.service.CustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customer")
class CustomerController(
    private val customerService: CustomerService
) {

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun getAll(@RequestParam name: String?): List<CustomerResponse> {
        return customerService.getAll(name).map { it.toResponse() }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid customer: PostCustomerRequest) {
        customerService.create(customer.toEntity())
    }

    @GetMapping("/{id}")
    @AccessOnlyForYourselfOrAdmin
    fun getCustomer(@PathVariable id: Int): CustomerResponse {
        return customerService.findById(id).toResponse()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @AccessOnlyForYourselfOrAdmin
    fun update(@PathVariable id: Int, @RequestBody @Valid customer: PutCustomerRequest) {
        val customerSaved = customerService.findById(id)
        customerService.update(customer.toEntity(customerSaved))
    }

    @DeleteMapping("/{id}")
    @AccessOnlyForYourselfOrAdmin
    fun delete(@PathVariable id: Int) {
        customerService.delete(id)
    }
}