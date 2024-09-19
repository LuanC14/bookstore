package dev.luan.bookstore.exception

class ForbbidenException(override var message: String, var internalCode: String): Exception() {
}