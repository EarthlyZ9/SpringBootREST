package com.earthlyz9.restfulwebservice.user;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// business logic -> Service
@Service
public class UserDaoService {

  private static List<User> users = new ArrayList<>();
  private static int usersCount = 3;

  static {
    users.add(new User(1, "Kenneth", LocalDate.of(2000, 1, 6), "pass1", "900909-1234567",
        LocalDateTime.now()));
    users.add(new User(2, "Alice", LocalDate.of(2000, 7, 29), "pass2", "000729-1234567",
        LocalDateTime.now()));
    users.add(new User(3, "Elena", LocalDate.of(2000, 9, 20), "pass3", "010908-1234567",
        LocalDateTime.now()));
  }

  public List<User> getAllUsers() {
    return users;
  }

  public User saveUser(User user) {
    if (user.getId() == null) {
      user.setId(++usersCount);
    }

    user.setJoinDate(LocalDateTime.now());
    users.add(user);

    return user;
  }

  public User findUserById(int id) {
    for (User user : users) {
      if (user.getId() == id) {
        return user;
      }
    }

    return null;
  }

  public User deleteUserById(int id) {
    Iterator<User> iterator = users.iterator();

    while (iterator.hasNext()) {
      User user = iterator.next();

      if (user.getId() == id) {
        iterator.remove();
        return user;
      }
    }

    return null;
  }

  public User changeUserName(int id, String newName) {
    User user = findUserById(id);

    if (user != null) {
      user.setName(newName);
      return user;
    }

    return null;
  }
}
