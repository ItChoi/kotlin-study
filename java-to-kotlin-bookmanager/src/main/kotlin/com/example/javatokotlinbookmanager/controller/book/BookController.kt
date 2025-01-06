package com.example.javatokotlinbookmanager.controller.book

import com.example.javatokotlinbookmanager.dto.book.request.BookLoanRequest
import com.example.javatokotlinbookmanager.dto.book.request.BookRequest
import com.example.javatokotlinbookmanager.dto.book.request.BookReturnRequest
import com.example.javatokotlinbookmanager.dto.book.response.BookStatResponse
import com.example.javatokotlinbookmanager.service.book.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController @Autowired constructor(
    private val bookService: BookService
) {

    @PostMapping("/book")
    fun saveBook(@RequestBody request: BookRequest) {
        bookService.saveBook(request)
    }

    @PostMapping("/book/loan")
    fun loanBook(@RequestBody request: BookLoanRequest) {
        bookService.loanBook(request)
    }

    @PutMapping("/book/return")
    fun returnBook(@RequestBody request: BookReturnRequest): Unit {
        bookService.returnBook(request)
    }

    @GetMapping("/book/loan")
    fun countLoadedBook(): Int {
        return bookService.countLoadedBook()
    }

    @GetMapping("/book/stat")
    fun getBookStatistics(): List<BookStatResponse> {
        return bookService.getBookStatistics()
    }
}