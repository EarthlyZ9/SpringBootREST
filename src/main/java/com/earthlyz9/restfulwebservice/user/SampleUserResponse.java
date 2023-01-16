package com.earthlyz9.restfulwebservice.user;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class SampleUserResponse {

  @Schema(description = "id")
  private Integer id;
  @Schema(description = "id")
  private String name;
  @Schema(description = "date when user was born")
  private LocalDate birthDate;
  @Schema(description = "date when user joined", defaultValue = "joined datetime")
  private LocalDate joinDate;
}
