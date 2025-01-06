package com.example.javatokotlinbookmanager.domain.user.loanhistory

import com.example.javatokotlinbookmanager.domain.user.User
import jakarta.persistence.*

@Entity
class UserLoanHistory(
    @ManyToOne
    val user: User,
    val bookName: String,
    var status: UserLoanStatus = UserLoanStatus.LOANED,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {
    val isReturn: Boolean
        get() = this.status == UserLoanStatus.RETURNED

    fun doReturn(): Unit {
        this.status = UserLoanStatus.RETURNED;
    }

    companion object {
        fun fixture(
            user: User,
            bookName: String = "이상한 나라의 앨리스",
            status: UserLoanStatus = UserLoanStatus.LOANED,
            id: Long? = null,
        ): UserLoanHistory {
            return UserLoanHistory(
                user = user,
                bookName = bookName,
                status = status,
                id = id,
            )
        }
    }

}