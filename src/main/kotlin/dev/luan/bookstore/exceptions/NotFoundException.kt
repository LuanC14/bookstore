package dev.luan.bookstore.exceptions

class NotFoundException(override var message: String, var internalCode: String): Exception() {
}