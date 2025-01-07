package com.example.javatokotlinbookmanager.domain.book

import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long> {
    fun findByName(bookName: String): Book?

//    @Query("SELECT NEW com.example.javatokotlinbookmanager.dto.book.response.BookStatResponse(b.type, COUNT(b.id)) FROM Book b GROUP BY b.type")
//    fun getStats(): List<BookStatResponse>
}