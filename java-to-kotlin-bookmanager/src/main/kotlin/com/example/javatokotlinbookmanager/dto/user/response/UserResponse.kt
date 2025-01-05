package com.example.javatokotlinbookmanager.dto.user.response

import com.example.javatokotlinbookmanager.domain.user.User

data class UserResponse(
    val id: Long,
    val name: String,
    val age: Int?
) {

    // 정적 팩토리 메서드 활용
    /*constructor(user: User): this(
        id = user.id!!,
        name = user.name,
        age = user.age
    )*/

    companion object {
        fun of(user: User): UserResponse {
            return UserResponse(
                id = user.id!!,
                name = user.name,
                age = user.age
            )
        }
    }
}
