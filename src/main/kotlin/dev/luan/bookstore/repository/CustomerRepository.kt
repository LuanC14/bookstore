package dev.luan.bookstore.repository

import dev.luan.bookstore.entity.CustomerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : JpaRepository<CustomerEntity, Int> {

    fun findByNameContaining(name: String): List<CustomerEntity>
    fun existsByEmail(email: String): Boolean;

    fun findByEmail(email: String): CustomerEntity?;
}