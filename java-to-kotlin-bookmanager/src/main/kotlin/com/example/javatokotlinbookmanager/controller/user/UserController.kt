package com.example.javatokotlinbookmanager.controller.user

import com.example.javatokotlinbookmanager.dto.user.request.UserCreateRequest
import com.example.javatokotlinbookmanager.dto.user.request.UserUpdateRequest
import com.example.javatokotlinbookmanager.dto.user.response.UserLoanHistoryResponse
import com.example.javatokotlinbookmanager.dto.user.response.UserResponse
import com.example.javatokotlinbookmanager.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController @Autowired constructor(
    private val userService: UserService
) {
    @PostMapping("/user")
    fun saveUser(@RequestBody request: UserCreateRequest): Unit {
        userService.saveUser(request)
    }

    /*@GetMapping("/user")
    fun getUsers(): List<UserResponse> {
        return userService.getUsers()
    }*/
    // 이렇게도 가능하다.
    @GetMapping("/user")
    fun getUsers(): List<UserResponse> = userService.getUsers()


    @PutMapping("/user")
    fun updateUserName(@RequestBody request: UserUpdateRequest) {
        userService.updateUserName(request)
    }

    @DeleteMapping("/user")
    fun deleteUser(@RequestParam name: String) {
        userService.deleteUser(name)
    }

    @GetMapping("/user/loan")
    fun getUserLoanHistories(): List<UserLoanHistoryResponse> {
        return userService.getUserLoanHistories()
    }
}