package com.example.javatokotlinbookmanager.service.book

import com.example.javatokotlinbookmanager.domain.book.Book
import com.example.javatokotlinbookmanager.domain.book.BookRepository
import com.example.javatokotlinbookmanager.domain.book.BookType
import com.example.javatokotlinbookmanager.domain.user.User
import com.example.javatokotlinbookmanager.domain.user.UserRepository
import com.example.javatokotlinbookmanager.domain.user.loanhistory.UserLoanHistory
import com.example.javatokotlinbookmanager.domain.user.loanhistory.UserLoanHistoryRepository
import com.example.javatokotlinbookmanager.domain.user.loanhistory.UserLoanStatus
import com.example.javatokotlinbookmanager.dto.book.request.BookLoanRequest
import com.example.javatokotlinbookmanager.dto.book.request.BookRequest
import com.example.javatokotlinbookmanager.dto.book.request.BookReturnRequest
import com.example.javatokotlinbookmanager.dto.book.response.BookStatResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookServiceTest @Autowired constructor(
    private val bookService: BookService,
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
) {

    @AfterEach
    fun clean() {
        bookRepository.deleteAll()
        userRepository.deleteAll()
    }

    @Test
    @DisplayName("책 등록이 정상 동작한다.")
    fun saveBookTest() {
        //given
        val request = BookRequest("이상한 나라의 엘리스", BookType.COMPUTER)

        //when
        bookService.saveBook(request)

        //then
        val books = bookRepository.findAll()
        assertThat(books).hasSize(1)
        assertThat(books[0].name).isEqualTo("이상한 나라의 엘리스")
        assertThat(books[0].type).isEqualTo(BookType.COMPUTER)
    }

    @Test
    @DisplayName("책 대출이 정상 동작한다")
    fun loanBookTest() {
        //given
        bookRepository.save(Book.fixture("이상한 나라의 엘리스", BookType.COMPUTER))
        val savedUser = userRepository.save(User("최태현", null))
        val request = BookLoanRequest("최태현", "이상한 나라의 엘리스")

        //when
        bookService.loanBook(request)

        //then
        val results = userLoanHistoryRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].bookName).isEqualTo("이상한 나라의 엘리스")
        assertThat(results[0].user.id).isEqualTo(savedUser.id)
        assertThat((results[0].status)).isEqualTo(UserLoanStatus.LOANED)
    }

    @Test
    @DisplayName("책이 진작 대출되어 있다면, 신규 대출이 실패한다")
    fun loanBookFailTest() {
        //given
        bookRepository.save(Book.fixture("이상한 나라의 엘리스", BookType.COMPUTER))
        val savedUser = userRepository.save(User("최태현", null))
        userLoanHistoryRepository.save(UserLoanHistory.fixture(savedUser, "이상한 나라의 엘리스"))
        val request = BookLoanRequest("최태현", "이상한 나라의 엘리스")

        //when & then
        val message = assertThrows<IllegalArgumentException> {
            bookService.loanBook(request)
        }.message
        assertThat(message).isEqualTo("진작 대출되어 있는 책입니다")
    }

    @Test
    @DisplayName("책 반납이 정상 동작한다")
    fun returnBookTest() {
        //bookRepository.save(JavaBook("이상한 나라의 엘리스"))
        val savedUser = userRepository.save(User("최태현", null))
        userLoanHistoryRepository.save(UserLoanHistory.fixture(savedUser, "이상한 나라의 엘리스"))
        val request = BookReturnRequest("최태현", "이상한 나라의 엘리스")

        bookService.returnBook(request)

        val results = userLoanHistoryRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].status).isEqualTo(UserLoanStatus.RETURNED)
    }

    @Test
    @DisplayName("책 대여 권수를 정상 확인한다")
    fun countLoanedBookTest(): Unit {
        //given
        val savedUser = userRepository.save(User("최태현", null))
        userLoanHistoryRepository.saveAll(
            listOf(
                UserLoanHistory.fixture(savedUser, "A"),
                UserLoanHistory.fixture(savedUser, "B", UserLoanStatus.RETURNED),
                UserLoanHistory.fixture(savedUser, "C", UserLoanStatus.RETURNED),
            )
        )

        //when
        val result = bookService.countLoadedBook()

        //then
        assertThat(result).isEqualTo(1)
    }

    @Test
    @DisplayName("분야별 책 권수를 정상 확인한다")
    fun getBookStatisticsTest() {
        //given
        bookRepository.saveAll(
            listOf(
                Book("A", BookType.COMPUTER),
                Book("B", BookType.COMPUTER),
                Book("C", BookType.SCIENCE),
            )
        )

        //when
        val results = bookService.getBookStatistics()

        //then
        assertThat(results).hasSize(2)
        //val computerDto = results.first{ result -> result.type == BookType.COMPUTER}
        //assertThat(computerDto.count).isEqualTo(2)
        assertCount(results, BookType.COMPUTER, 2L)

        //val scienceDto = results.first{ result -> result.type == BookType.SCIENCE}
        //assertThat(scienceDto.count).isEqualTo(1)
        assertCount(results, BookType.SCIENCE, 1L)
    }

    private fun assertCount(
        results: List<BookStatResponse>,
        type: BookType,
        count: Long
    ) {
        assertThat(results.first() { result -> result.type == type }.count).isEqualTo(count)
    }

}