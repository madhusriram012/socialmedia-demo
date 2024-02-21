package com.madhu.socialmedia.Instagram.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.madhu.socialmedia.Instagram.jpa.PostRepository;
import com.madhu.socialmedia.Instagram.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJPAController {

	UserRepository userRepository;
	PostRepository postRepository;

	public UserJPAController(UserRepository repository, PostRepository postRepository) {
		super();
		this.userRepository = repository;
		this.postRepository = postRepository;

	}

	@GetMapping("/jpa/users")
	public List<User> listAll() {
		return userRepository.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> getUserByID(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);

		if (user.isEmpty())
			throw new com.madhu.socialmedia.Instagram.exception.UserNotfoundException("id: " + id);

		EntityModel<User> entityModel = EntityModel.of(user.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).listAll());
		entityModel.add(link.withRel("all-users"));

		return entityModel;
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id,@Valid @RequestBody Post post) {
		Optional<User> user = userRepository.findById(id);

		if (user.isEmpty())
			throw new com.madhu.socialmedia.Instagram.exception.UserNotfoundException("id: " + id);

		post.setUser(user.get());
		Post savedPost = postRepository.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
				.toUri();
		return ResponseEntity.created(location).build();
		
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteById(@PathVariable int id) {
		userRepository.deleteById(id);
	}

	// posts
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> listAllPosts(@PathVariable int id) {

		Optional<User> user = userRepository.findById(id);

		if (user.isEmpty())
			throw new com.madhu.socialmedia.Instagram.exception.UserNotfoundException("id: " + id);

		return user.get().getPosts();
	}
}
