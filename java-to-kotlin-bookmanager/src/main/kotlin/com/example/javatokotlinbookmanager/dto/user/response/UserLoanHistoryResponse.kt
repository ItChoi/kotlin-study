package com.example.javatokotlinbookmanager.dto.user.response

import com.example.javatokotlinbookmanager.domain.user.User
import com.example.javatokotlinbookmanager.domain.user.loanhistory.UserLoanHistory

data class UserLoanHistoryResponse(
    val name: String, // 유저 이름
    val books: List<BookHistoryResponse>,
) {
    companion object {
        fun of(user: User): UserLoanHistoryResponse {
            return UserLoanHistoryResponse(
                name = user.name,
                books = user.userLoanHistories.map(BookHistoryResponse::of)
            )
        }
    }
}

data class BookHistoryResponse(
    val name: String, // 책 이름
    val isReturn: Boolean,
) {
    // 정적 팩토리 메서드
    companion object {
        fun of(history: UserLoanHistory): BookHistoryResponse {
            return BookHistoryResponse(
                name = history.bookName,
                //isReturn = history.status == UserLoanStatus.RETURNED
                isReturn = history.isReturn
            )
        }
    }
}
