package com.earthlyz9.restfulwebservice.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class UserResourceAssembler implements
    RepresentationModelAssembler<User, EntityModel<User>> {

  @Override
  public CollectionModel<EntityModel<User>> toCollectionModel(Iterable<? extends User> entities) {
    return RepresentationModelAssembler.super.toCollectionModel(entities);
  }

  @Override
  public EntityModel<User> toModel(User user) {
    int userId = user.getId();

    EntityModel<User> entityModel = EntityModel.of(user,
        linkTo(methodOn(UserController.class).retrieveUserById(userId)).withSelfRel());
    entityModel.add(
        linkTo(methodOn(UserController.class).retrieveUserById(userId + 1)).withRel("next"));

    if (userId > 1) {
      entityModel.add(
          linkTo(methodOn(UserController.class).retrieveUserById(userId - 1)).withRel("previous"));
    }

    return entityModel;
  }
}
