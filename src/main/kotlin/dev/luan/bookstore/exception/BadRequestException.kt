package dev.luan.bookstore.exception

class BadRequestException(override var message: String, var internalCode: String): Exception() {
}