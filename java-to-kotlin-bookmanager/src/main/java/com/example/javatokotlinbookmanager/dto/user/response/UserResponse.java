package com.example.javatokotlinbookmanager.dto.user.response;

import com.example.javatokotlinbookmanager.domain.user.User;

public class UserResponse {

  private final long id;
  private final String name;
  private final Integer age;

  public UserResponse(User user) {
    this.id = user.getId();
    this.name = user.getName();
    this.age = user.getAge();
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Integer getAge() {
    return age;
  }

}
