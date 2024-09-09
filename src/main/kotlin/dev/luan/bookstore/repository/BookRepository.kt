package dev.luan.bookstore.repository

import dev.luan.bookstore.enum.BookStatus
import dev.luan.bookstore.entity.BookEntity
import dev.luan.bookstore.entity.CustomerEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<BookEntity, Int> {

    fun findByStatus(status: BookStatus, pageable: Pageable): Page<BookEntity>
    fun findByCustomer(customer: CustomerEntity): List<BookEntity>

//    fun findAll(pageable: Pageable): Page<BookModel>

}