package dev.luan.bookstore.entity

import dev.luan.bookstore.enum.CustomerStatus
import dev.luan.bookstore.enum.ProfileRoles
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
    var status: CustomerStatus,

    @Column
    val password: String,

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = ProfileRoles::class, fetch = FetchType.EAGER)
    @CollectionTable(name = "customer_roles", joinColumns = [JoinColumn(name = "customer_id")])
    val roles: Set<ProfileRoles> = setOf()
)