package com.example.javatokotlinbookmanager.domain.user.loanhistory

import com.example.javatokotlinbookmanager.domain.user.User
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
class UserLoanHistory(
    @ManyToOne
    val user: User,
    val bookName: String,
    var isReturn: Boolean,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {
    fun doReturn(): Unit {
        this.isReturn = true;
    }

}