package com.earthlyz9.restfulwebservice.user;

import com.earthlyz9.restfulwebservice.exceptions.UserNotFoundException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

  private UserDaoService service;

  // 생성자를 사용한 의존성 주입
  public AdminUserController(UserDaoService service) {
    this.service = service;
  }

  @GetMapping(path = "/users")
  public MappingJacksonValue listAllUsers() {

    List<User> users = service.getAllUsers();
    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name",
        "joinDate", "ssn");

    FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

    MappingJacksonValue mapping = new MappingJacksonValue(users);
    mapping.setFilters(filters);

    return mapping;
  }

  //  @GetMapping(path = "/v1/users/{id}")
  //  @GetMapping(value="/users/{id}/", params="version=1")
  @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=1")
  public MappingJacksonValue retrieveUserByIdV1(@PathVariable int id) {
    User user = service.findUserById(id);
    if (user == null) {
      throw new UserNotFoundException();
    }

    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name",
        "joinDate", "ssn");

    FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

    MappingJacksonValue mapping = new MappingJacksonValue(user);
    mapping.setFilters(filters);

    return mapping;
  }


  // @GetMapping(path = "/v2/users/{id}")
  // @GetMapping(value="/users/{id}/", params="version=2")
  @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=2")
  public MappingJacksonValue retrieveUserByIdV2(@PathVariable int id) {
    User user = service.findUserById(id);
    if (user == null) {
      throw new UserNotFoundException();
    }

    UserV2 userV2 = new UserV2();
    // User -> User2
    BeanUtils.copyProperties(user, userV2);
    userV2.setGrade("VIP");

    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name",
        "joinDate", "ssn", "grade");

    FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);

    MappingJacksonValue mapping = new MappingJacksonValue(userV2);
    mapping.setFilters(filters);

    return mapping;
  }
}
