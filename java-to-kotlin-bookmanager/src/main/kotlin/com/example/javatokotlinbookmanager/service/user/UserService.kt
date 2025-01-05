package com.example.javatokotlinbookmanager.service.user

import com.example.javatokotlinbookmanager.domain.user.User
import com.example.javatokotlinbookmanager.domain.user.UserRepository
import com.example.javatokotlinbookmanager.dto.user.request.UserCreateRequest
import com.example.javatokotlinbookmanager.dto.user.request.UserUpdateRequest
import com.example.javatokotlinbookmanager.dto.user.response.UserResponse
import com.example.javatokotlinbookmanager.util.fail
import com.example.javatokotlinbookmanager.util.findByIdOrThrow
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException

@Service
class UserService(
    private val userRepository: UserRepository
) {

    @Transactional
    fun saveUser(request: UserCreateRequest) {
        val newUser = User(request.name, request.age)
        userRepository.save(newUser)
    }

    @Transactional(readOnly = true)
    fun getUsers(): List<UserResponse> {
        return userRepository.findAll()
            //.map { UserResponse(it) } // 파라미터가 하나인 람다는 it을 이용해 활용 가능
            //.map { user -> UserResponse(user) } // 강사 선호 방식
            //.map(::UserResponse) // map으로 들어온 user 인자로 바로 생성자 호출할 수 있다.
            .map { user -> UserResponse.of(user) }

    }

    @Transactional
    fun updateUserName(request: UserUpdateRequest): Unit {
        //val user = userRepository.findByIdOrNull(request.id) ?: fail()
        /*val user = userRepository.findById(request.id)
            //.orElseThrow({ IllegalArgumentException() })
            .orElseThrow(::IllegalArgumentException)*/
        val user = userRepository.findByIdOrThrow(request.id)
        user.updateName(request.name)
    }

    @Transactional
    fun deleteUser(name: String): Unit {
        val user = userRepository.findByName(name)
            //.orElseThrow({ IllegalArgumentException() })
            //.orElseThrow(::IllegalArgumentException)
            //?: throw IllegalArgumentException()
            ?: fail()
        userRepository.delete(user)
    }

}