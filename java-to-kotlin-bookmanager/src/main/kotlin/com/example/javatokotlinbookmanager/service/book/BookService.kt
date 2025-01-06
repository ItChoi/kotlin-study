package com.example.javatokotlinbookmanager.service.book

import com.example.javatokotlinbookmanager.domain.book.Book
import com.example.javatokotlinbookmanager.domain.book.BookRepository
import com.example.javatokotlinbookmanager.domain.user.UserRepository
import com.example.javatokotlinbookmanager.domain.user.loanhistory.UserLoanHistoryRepository
import com.example.javatokotlinbookmanager.domain.user.loanhistory.UserLoanStatus
import com.example.javatokotlinbookmanager.dto.book.request.BookLoanRequest
import com.example.javatokotlinbookmanager.dto.book.request.BookRequest
import com.example.javatokotlinbookmanager.dto.book.request.BookReturnRequest
import com.example.javatokotlinbookmanager.dto.book.response.BookStatResponse
import com.example.javatokotlinbookmanager.util.fail
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService(
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
) {

    @Transactional
    fun saveBook(request: BookRequest) {
        val book = Book(request.name, request.type)
        bookRepository.save(book)
    }

    @Transactional
    fun loanBook(request: BookLoanRequest) {
        //val book = bookRepository.findByName(request.bookName).orElseThrow(::IllegalArgumentException)
        //val book = bookRepository.findByName(request.bookName) ?: throw IllegalArgumentException()
        val book = bookRepository.findByName(request.bookName) ?: fail()
        if (userLoanHistoryRepository.findByBookNameAndStatus(request.bookName, UserLoanStatus.LOANED) != null) {
            throw IllegalArgumentException("진작 대출되어 있는 책입니다")
        }

        //val user = userRepository.findByName(request.bookName).orElseThrow(::IllegalArgumentException)
        //val user = userRepository.findByName(request.bookName) ?: throw IllegalArgumentException()
        val user = userRepository.findByName(request.userName) ?: fail()
        user.loanBook(book)
    }

    @Transactional
    fun returnBook(request: BookReturnRequest) {
        //val user = userRepository.findByName(request.bookName).orElseThrow(::IllegalArgumentException)
        //val user = userRepository.findByName(request.bookName) ?: throw IllegalArgumentException()
        val user = userRepository.findByName(request.userName) ?: fail()
        user.returnBook(request.bookName)
    }

    @Transactional(readOnly = true)
    fun countLoadedBook(): Int {
        return userLoanHistoryRepository.findAllByStatus(UserLoanStatus.LOANED).size
    }

    @Transactional(readOnly = true)
    fun getBookStatistics(): List<BookStatResponse> {
        val results = mutableListOf<BookStatResponse>()
        val books = bookRepository.findAll()
        /*for (book in books) {
            val targetDto = results.firstOrNull { dto -> book.type == dto.type }
            if (targetDto == null) {
                results.add(BookStatResponse(book.type, 1))
            } else {
                targetDto.plusOne()
            }
        }*/
        // 코틀린을 활용한 리팩토링
        for (book in books) {
            // 존재하는 경우에만 plusOne() 실행
            // null인 경우 elvis 연산자를 통해 add
            // 아래가 깔끔해졌지만, 좋은 코드라고 할 순 없다.
            results.firstOrNull { dto -> book.type == dto.type }?.plusOne()
                ?: results.add(BookStatResponse(book.type, 1))
        }

        return results
    }

}