package com.example.javatokotlinbookmanager.controller.user;

import com.example.javatokotlinbookmanager.dto.user.request.UserCreateRequest;
import com.example.javatokotlinbookmanager.dto.user.request.UserUpdateRequest;
import com.example.javatokotlinbookmanager.dto.user.response.UserResponse;
import com.example.javatokotlinbookmanager.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/user")
  public void saveUser(@RequestBody UserCreateRequest request) {
    userService.saveUser(request);
  }

  @GetMapping("/user")
  public List<UserResponse> getUsers() {
    return userService.getUsers();
  }

  @PutMapping("/user")
  public void updateUserName(@RequestBody UserUpdateRequest request) {
    userService.updateUserName(request);
  }

  @DeleteMapping("/user")
  public void deleteUser(@RequestParam String name) {
    userService.deleteUser(name);
  }

}
