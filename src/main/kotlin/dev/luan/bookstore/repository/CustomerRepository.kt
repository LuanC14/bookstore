package dev.luan.bookstore.repository

import dev.luan.bookstore.entity.CustomerEntity
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<CustomerEntity, Int> {

    fun findByNameContaining(name: String): List<CustomerEntity>
    fun existsByEmail(email: String): Boolean;
}