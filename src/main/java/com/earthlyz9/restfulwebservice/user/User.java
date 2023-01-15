package com.earthlyz9.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;


@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value={"password", "ssn"})
@JsonFilter("UserInfo")
public class User extends RepresentationModel<User> {

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
