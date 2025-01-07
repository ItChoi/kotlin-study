package com.example.javatokotlinbookmanager.repository.user.loanhistory

import com.example.javatokotlinbookmanager.domain.user.loanhistory.QUserLoanHistory.userLoanHistory
import com.example.javatokotlinbookmanager.domain.user.loanhistory.UserLoanHistory
import com.example.javatokotlinbookmanager.domain.user.loanhistory.UserLoanStatus
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

@Component
class UserLoanHistoryQuerydslRepository(
    private val queryFactory: JPAQueryFactory
) {
    fun find(
        bookName: String,
        status: UserLoanStatus? = null,
    ): UserLoanHistory? {
        return queryFactory.select(userLoanHistory)
            .from(userLoanHistory)
            .where(
                userLoanHistory.bookName.eq(bookName),
                status?.let { userLoanHistory.status.eq(status) }
            )
            .limit(1)
            .fetchOne()
    }

    fun count(
        status: UserLoanStatus
    ): Long {
        return queryFactory.select(userLoanHistory.id.count())
            .from(userLoanHistory)
            .where(
                userLoanHistory.status.eq(status)
            )
            .fetchOne() ?: 0L
    }
}