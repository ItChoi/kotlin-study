package com.example.javatokotlinbookmanager.domain.user

interface UserRepositoryCustom {
    fun findAllWithHistories(): List<User>
}