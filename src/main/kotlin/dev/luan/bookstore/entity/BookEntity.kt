package dev.luan.bookstore.entity

import dev.luan.bookstore.enum.BookStatus
import dev.luan.bookstore.enum.Errors
import dev.luan.bookstore.exception.BadRequestException
import jakarta.persistence.*
import java.math.BigDecimal

@Entity(name = "book")
data class BookEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var price: BigDecimal,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerEntity? = null

) {

    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null
        set(value) {
            if(field == BookStatus.CANCELADO || field == BookStatus.DELETADO)
                throw BadRequestException(Errors.BS202.message.format(field), Errors.BS202.code)

            field = value
        }

    // Segundo construtor usado por conta da propriedade Status possuir um set personalizado, impossibilitando de ficar dentro do construtor default da data class
    constructor(id: Int? = null,
                name: String,
                price: BigDecimal,
                customer: CustomerEntity? = null,
                status: BookStatus?): this(id, name, price, customer) { // Esse This se refere ao constructor default da Data Class
        this.status = status
    }

}