package com.example.javatokotlinbookmanager.domain.user.loanhistory

import org.springframework.data.jpa.repository.JpaRepository

interface UserLoanHistoryRepository : JpaRepository<UserLoanHistory, Long> {
    // querydsl 대체
//    fun findByBookName(bookName: String): UserLoanHistory
//
//    fun findByBookNameAndStatus(
//        bookName: String,
//        status: UserLoanStatus
//    ): UserLoanHistory?
//
//    fun findAllByStatus(status: UserLoanStatus): List<UserLoanHistory>
//
//    fun countByStatus(status: UserLoanStatus): Long
}