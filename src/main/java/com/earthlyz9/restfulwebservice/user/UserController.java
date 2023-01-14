package com.earthlyz9.restfulwebservice.user;

import com.earthlyz9.restfulwebservice.exceptions.BadRequestException;
import com.earthlyz9.restfulwebservice.exceptions.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

  private UserDaoService service;

  // 생성자를 사용한 의존성 주입
  public UserController(UserDaoService service) {
    this.service = service;
  }

  @GetMapping(path = "/users")
  public List<User> listAllUsers() {
    return service.getAllUsers();
  }

  @GetMapping(path = "/users/{id}")
  public User retrieveUserById(@PathVariable int id) {
    User user = service.findUserById(id);
    if (user == null) {
      throw new UserNotFoundException();
    } else {
      return user;
    }
  }

  @PostMapping(path = "/users")
  public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
    User savedUser = service.saveUser(user);

    // Location header 에 추가된 유저를 조회할 수 있는 uri 가 부착됨
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
        .buildAndExpand(savedUser.getId()).toUri();

    // 201 상태코드와 유저 정보 반환
    return ResponseEntity.created(location).body(savedUser);
  }

  @DeleteMapping(path = "/users/{id}")
  public User deleteUser(@PathVariable int id) {
    User user = service.deleteUserById(id);
    if (user == null) {
      throw new UserNotFoundException();
    }

    return user;
  }

  @PatchMapping(path = "/users/{id}")
  public User updateUser(@PathVariable int id, @RequestBody User user) {
    User updatedUser = service.changeUserName(id, user.getName());
    if (updatedUser == null) {
      throw new BadRequestException("name field is required");
    }

    return updatedUser;
  }
}
