package com.example.javatokotlinbookmanager.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<User, Long> {
    fun findByName(name: String): Optional<User>
}