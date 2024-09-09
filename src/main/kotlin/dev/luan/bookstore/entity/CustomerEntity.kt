package dev.luan.bookstore.entity

import dev.luan.bookstore.enum.CustomerStatus
import jakarta.persistence.*

@Entity(name = "customer")
data class CustomerEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var email: String,

    @Column
    @Enumerated(EnumType.STRING)
    var status: CustomerStatus
)