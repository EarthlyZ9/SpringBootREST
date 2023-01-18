package com.earthlyz9.restfulwebservice.post;

import com.earthlyz9.restfulwebservice.exceptions.CustomErrorCode;
import com.earthlyz9.restfulwebservice.exceptions.CustomException;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/posts")
public class PostController {

  private final PostRepository postRepository;
  private final PostResourceAssembler assembler;

  public PostController(PostRepository postRepository, PostResourceAssembler assembler) {
    this.postRepository = postRepository;
    this.assembler = assembler;
  }

  @GetMapping("")
  public CollectionModel<EntityModel<Post>> retrieveAllPosts() {
    List<Post> posts = postRepository.findAll();
    return assembler.toCollectionModel(posts);
  }

  @GetMapping("/{id}")
  public EntityModel<Post> retrievePostById(@PathVariable int id) {
    Optional<Post> post = postRepository.findById(id);
    if (post.isEmpty()) {
      throw new CustomException(CustomErrorCode.INSTANCE_NOT_FOUND);
    }
    return assembler.toModel(post.get());
  }

  @PostMapping("")
  public ResponseEntity<EntityModel<Post>> createPost(@Valid @RequestBody Post post) {
    Post savedPost = postRepository.save(post);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
        .buildAndExpand(savedPost.getId()).toUri();
    return ResponseEntity.created(location).body(assembler.toModel(savedPost));
  }
}
