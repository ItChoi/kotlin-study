package com.example.javatokotlinbookmanager.dto.book.response

import com.example.javatokotlinbookmanager.domain.book.BookType

data class BookStatResponse(
    val type: BookType,
    var count: Int,
) {
    fun plusOne() {
        count++
    }
}
