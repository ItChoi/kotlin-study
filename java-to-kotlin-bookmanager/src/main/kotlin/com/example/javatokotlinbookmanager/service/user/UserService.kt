package com.example.javatokotlinbookmanager.service.user

import com.example.javatokotlinbookmanager.domain.user.User
import com.example.javatokotlinbookmanager.domain.user.UserRepository
import com.example.javatokotlinbookmanager.domain.user.loanhistory.UserLoanHistoryRepository
import com.example.javatokotlinbookmanager.domain.user.loanhistory.UserLoanStatus
import com.example.javatokotlinbookmanager.dto.user.request.UserCreateRequest
import com.example.javatokotlinbookmanager.dto.user.request.UserUpdateRequest
import com.example.javatokotlinbookmanager.dto.user.response.BookHistoryResponse
import com.example.javatokotlinbookmanager.dto.user.response.UserLoanHistoryResponse
import com.example.javatokotlinbookmanager.dto.user.response.UserResponse
import com.example.javatokotlinbookmanager.util.fail
import com.example.javatokotlinbookmanager.util.findByIdOrThrow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository, private val userLoanHistoryRepository: UserLoanHistoryRepository
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

    @Transactional(readOnly = true)
    fun getUserLoanHistories(): List<UserLoanHistoryResponse> {
        //return userRepository.findAll().map { user ->
        /*return userRepository.findAllWithHistories().map { user ->
            UserLoanHistoryResponse(
                name = user.name,
                books = user.userLoanHistories.map { history ->
                    BookHistoryResponse(
                        name = history.bookName,
                        isReturn =  history.status == UserLoanStatus.RETURNED
                    )
                }
            )
        }*/
        // 정적 팩토리 메서드 활용
        /*return userRepository.findAllWithHistories().map { user ->
            UserLoanHistoryResponse(
                name = user.name,
                *//*books = user.userLoanHistories.map { history ->
                    BookHistoryResponse.of(history)
                }*//*
                books = user.userLoanHistories.map(BookHistoryResponse::of),
            )
        }*/
        return userRepository.findAllWithHistories().map(UserLoanHistoryResponse::of)
    }

}