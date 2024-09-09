package dev.luan.bookstore.enum

enum class Errors(val code: String, val message: String) {

    BS0101("BS-0101", "Invalid Request"),

    BS1001("BS-1001", "Book [%s] not exists"),
    BS1002("BS-1002", "Cannot update book with status [%s]"),


    BS1101("BS-0002", "Customer [%s] not exists")
}