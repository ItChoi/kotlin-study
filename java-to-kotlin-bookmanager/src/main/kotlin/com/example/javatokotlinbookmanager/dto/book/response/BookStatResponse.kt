package com.example.javatokotlinbookmanager.dto.book.response

import com.example.javatokotlinbookmanager.domain.book.BookType

data class BookStatResponse(
    val type: BookType,
    val count: Long,
//    var count: Int,
) {
//    fun plusOne() {
//        count++
//    }
}
