package com.earthlyz9.restfulwebservice.post;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PostResourceAssembler implements
    RepresentationModelAssembler<Post, EntityModel<Post>> {

  @Override
  public CollectionModel<EntityModel<Post>> toCollectionModel(Iterable<? extends Post> posts) {
    return RepresentationModelAssembler.super.toCollectionModel(posts).add(
        linkTo(methodOn(PostController.class).retrieveAllPosts()).withSelfRel()
    );
  }

  @Override
  public EntityModel<Post> toModel(Post post) {
    int postId = post.getId();

    EntityModel<Post> entityModel = EntityModel.of(post,
        linkTo(methodOn(PostController.class).retrievePostById(postId)).withSelfRel());
    entityModel.add(
        linkTo(methodOn(PostController.class).retrievePostById(postId + 1)).withRel("next"));

    if (postId > 1) {
      entityModel.add(
          linkTo(methodOn(PostController.class).retrievePostById(postId - 1)).withRel("previous"));
    }

    return entityModel;
  }
}
