package dev.luan.bookstore.exceptions

class BadRequestException(override var message: String, var internalCode: String): Exception() {
}