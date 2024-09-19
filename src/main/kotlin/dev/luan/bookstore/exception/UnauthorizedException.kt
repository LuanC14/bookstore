package dev.luan.bookstore.exception

class UnauthorizedException(override var message: String, var internalCode: String): Exception() {
}