package dev.luan.bookstore.controller

import dev.luan.bookstore.controller.request.PostBookRequest
import dev.luan.bookstore.controller.request.PutBookRequest
import dev.luan.bookstore.controller.response.BookResponse
import dev.luan.bookstore.controller.response.PageResponse
import dev.luan.bookstore.extension.toEntity
import dev.luan.bookstore.extension.toPageResponse
import dev.luan.bookstore.extension.toResponse
import dev.luan.bookstore.service.BookService
import dev.luan.bookstore.service.CustomerService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/book")
class BookController(
    private val bookService: BookService,
    private val customerService: CustomerService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: PostBookRequest) {
        val customer = customerService.findById(request.customerId)
        bookService.create(request.toEntity(customer))
    }

    @GetMapping
    fun findAll(@PageableDefault(page = 0, size = 10) pageable: Pageable): PageResponse<BookResponse> {
        return bookService.findAll(pageable).map { it.toResponse() }.toPageResponse()
    }

    @GetMapping("/active")
    fun findActives(@PageableDefault(page = 0, size = 10) pageable: Pageable): PageResponse<BookResponse> =
        bookService.findActives(pageable).map { it.toResponse() }.toPageResponse()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): BookResponse {
        return bookService.findById(id).toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        bookService.delete(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody book: PutBookRequest) {
        val bookSaved = bookService.findById(id)
        bookService.update(book.toEntity(bookSaved))
    }

}