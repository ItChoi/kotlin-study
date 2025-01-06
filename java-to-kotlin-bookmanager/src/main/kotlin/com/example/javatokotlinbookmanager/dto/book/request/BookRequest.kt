package com.example.javatokotlinbookmanager.dto.book.request

import com.example.javatokotlinbookmanager.domain.book.BookType

data class BookRequest(
    val name: String,
    val type: BookType
)
