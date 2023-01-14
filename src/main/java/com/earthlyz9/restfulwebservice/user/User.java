package com.earthlyz9.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
//@JsonIgnoreProperties(value={"password", "ssn"})
@JsonFilter("UserInfo")
public class User {

  private Integer id;

  @Size(min = 2, message = "name should be min 2 in length")
  private String name;

  @Past
  @NotNull
  @NotEmpty
  private LocalDate birthDate;

  @Size(min = 5, message = "password should be min 5 in length")
  @NotNull
  @NotEmpty
//    @JsonIgnore
  private String password;

  @NotNull
  @NotEmpty
//    @JsonIgnore
  private String ssn;

  private LocalDateTime joinDate;
}
