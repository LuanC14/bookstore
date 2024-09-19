package dev.luan.bookstore.enum

enum class Errors(val code: String, val message: String) {

    BS000("BS-000", "Unauthorized"),
    BS001("BS-001", "Email or password invalid"),
    BS002("BS-002", "Inactive customer"),


    BS101("BS-101", "Invalid Request"),

    BS201("BS-201", "Book [%s] not exists"),
    BS202("BS-202", "Cannot update book with status [%s]"),
    BS303("BS-303", "The books [%s] are unavailable"),


    BS401("BS-401", "Customer [%s] not exists")
}