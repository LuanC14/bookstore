package dev.luan.bookstore.exception

class NotFoundException(override var message: String, var internalCode: String): Exception() {
}