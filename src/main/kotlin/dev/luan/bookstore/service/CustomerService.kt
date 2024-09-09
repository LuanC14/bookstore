package dev.luan.bookstore.service

import dev.luan.bookstore.enum.CustomerStatus
import dev.luan.bookstore.enum.Errors
import dev.luan.bookstore.exception.NotFoundException
import dev.luan.bookstore.entity.CustomerEntity
import dev.luan.bookstore.repository.CustomerRepository
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
) {

    fun getAll(name: String?): List<CustomerEntity> {
        name?.let {
            return customerRepository.findByNameContaining(it)
        }
        return customerRepository.findAll().toList()
    }

    fun create(customer: CustomerEntity) {
        customerRepository.save(customer)
    }

    fun findById(id: Int): CustomerEntity {
        return customerRepository.findById(id)
            .orElseThrow { NotFoundException(Errors.BS1101.message.format(id), Errors.BS1101.code) }
    }

    fun update(customer: CustomerEntity) {
        if (!customerRepository.existsById(customer.id!!)) {
            throw Exception()
        }

        customerRepository.save(customer)
    }

    fun delete(id: Int) {
        val customer = findById(id)
        bookService.deleteByCustomer(customer)

        customer.status = CustomerStatus.INATIVO

        customerRepository.save(customer)
    }

    fun verifyIfEmailIsAvailable(email: String): Boolean {
        return customerRepository.existsByEmail(email)
    }

}