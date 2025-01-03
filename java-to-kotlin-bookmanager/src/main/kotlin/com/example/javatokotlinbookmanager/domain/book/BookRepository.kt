package com.example.javatokotlinbookmanager.domain.book

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface BookRepository : JpaRepository<Book, Long> {
    fun findByName(bookName: String): Optional<Book>
}