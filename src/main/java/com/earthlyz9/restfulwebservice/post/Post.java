package com.earthlyz9.restfulwebservice.post;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "posts", itemRelation = "post")
@Entity
public class Post extends RepresentationModel<Post> {

  @Id
  @GeneratedValue
  private Integer id;

  @NotNull
  @NotEmpty
  @Size(max = 10)
  private String name;

  @NotNull
  @NotEmpty
  @Size(max = 20)
  private String title;

  @NotNull
  @NotEmpty
  @Size(max = 200)
  private String description;

  private LocalDate createdAt;
}
