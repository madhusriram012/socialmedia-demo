package com.madhu.socialmedia.Instagram.user;

import java.net.URI;
import java.util.List;

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
import jakarta.validation.Valid;

@RestController
public class UserController {
	UserDaoService userDaoService;

	public UserController(UserDaoService userDaoService) {
		super();
		this.userDaoService = userDaoService;
	}
	
	@GetMapping("/users")
	public List<User> listAll(){
		return userDaoService.findAll();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> getUserByID(@PathVariable int id){
		User user = userDaoService.retrieveByID(id);
		
		if(user==null)
			throw new com.madhu.socialmedia.Instagram.exception.UserNotfoundException("id: "+id);
		
		EntityModel<User> entityModel=EntityModel.of(user);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).listAll());
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}
	
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user){
		User savedUser=userDaoService.save(user);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteById(@PathVariable int id) {
		userDaoService.delete(id);
	}
	
}
