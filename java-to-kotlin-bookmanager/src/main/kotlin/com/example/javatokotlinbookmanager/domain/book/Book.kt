package com.example.javatokotlinbookmanager.domain.book

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.lang.IllegalArgumentException

@Entity
class Book(
    val name: String,

    // default 파라미터는 가장 아래로 내리는 것이 관례
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {
    init {
        if (this.name.isBlank()) {
            throw IllegalArgumentException("이름은 비어 있을 수 없습니다")
        }
    }
}