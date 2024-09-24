package dev.luan.bookstore.service

import dev.luan.bookstore.enum.CustomerStatus
import dev.luan.bookstore.enum.Errors
import dev.luan.bookstore.exception.NotFoundException
import dev.luan.bookstore.entity.CustomerEntity
import dev.luan.bookstore.enum.ProfileRoles
import dev.luan.bookstore.repository.CustomerRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
    private val bookService: BookService,
    private val bCrypt: BCryptPasswordEncoder
) {

    fun getAll(name: String?): List<CustomerEntity> {
        name?.let {
            return customerRepository.findByNameContaining(it)
        }
        return customerRepository.findAll().toList()
    }

    fun create(customer: CustomerEntity) {
        val copyCustomer = customer.copy(
            roles = setOf(ProfileRoles.CUSTOMER),
            password = bCrypt.encode(customer.password)
        );

        customerRepository.save(copyCustomer)
    }

    fun findById(id: Int): CustomerEntity {
        return customerRepository.findById(id)
            .orElseThrow { NotFoundException(Errors.BS401.message.format(id), Errors.BS401.code) }
    }

    fun update(customer: CustomerEntity) {
        if (!customerRepository.existsById(customer.id!!)) {
            throw NotFoundException(Errors.BS401.message.format(customer.id), Errors.BS401.code)
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