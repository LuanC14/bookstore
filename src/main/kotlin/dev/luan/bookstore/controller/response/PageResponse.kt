package dev.luan.bookstore.controller.response

class PageResponse<T>(
    val items: List<T>,
    val currentPage: Int,
    val totalItems: Long,
    val totalPages: Int
)