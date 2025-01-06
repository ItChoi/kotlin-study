package com.example.javatokotlinbookmanager.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<User, Long> {
    fun findByName(name: String): User?

    // JPQL 사용: @Query, 강사는 JPQL보다 QueryDSL 사용
    // left join fetch + distinct을 통해 N + 1 해결
    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.userLoanHistories")
    fun findAllWithHistories(): List<User>

}