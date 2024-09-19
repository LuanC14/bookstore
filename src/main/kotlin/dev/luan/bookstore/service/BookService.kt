package dev.luan.bookstore.service

import dev.luan.bookstore.enum.BookStatus
import dev.luan.bookstore.enum.Errors
import dev.luan.bookstore.exception.NotFoundException
import dev.luan.bookstore.entity.BookEntity
import dev.luan.bookstore.entity.CustomerEntity
import dev.luan.bookstore.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository
) {

    fun create(book: BookEntity) {
        bookRepository.save(book)
    }

    fun findAll(pageable: Pageable): Page<BookEntity> {
        return bookRepository.findAll(pageable)
    }

    fun findActives(pageable: Pageable): Page<BookEntity> {
        return bookRepository.findByStatus(BookStatus.ATIVO, pageable)
    }

    fun findById(id: Int): BookEntity {
        return bookRepository.findById(id).orElseThrow {
            NotFoundException(Errors.BS201.message.format(id), Errors.BS201.code)
        }
    }

    fun delete(id: Int) {
        val book = findById(id)

        book.status = BookStatus.CANCELADO

        update(book)
    }

    fun update(book: BookEntity) {
        bookRepository.save(book)
    }

    fun deleteByCustomer(customer: CustomerEntity) {
        val books = bookRepository.findByCustomer(customer)
        for (book in books) {
            book.status = BookStatus.DELETADO
        }
        bookRepository.saveAll(books)
    }

    fun findAllByIds(bookIds: Set<Int>): List<BookEntity> {
        return bookRepository.findAllById(bookIds).toList()
    }

    fun purchase(books: MutableList<BookEntity>) {
        books.map {
            it.status = BookStatus.VENDIDO
        }
        bookRepository.saveAll(books)
    }


}
