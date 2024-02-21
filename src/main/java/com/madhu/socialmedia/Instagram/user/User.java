package com.madhu.socialmedia.Instagram.user;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;



@Entity(name = "user_details")
public class User {
	
	public User() {
		
	}
	@Size(min = 2,message = "Name should have atleast 2 characters")
	private String username;
	
	@Id
	@GeneratedValue
	private Integer id;
	@Past(message = "Should enter only past date")
	private LocalDate birthDate;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Post> posts;
	
	
	public User(String username, Integer id, LocalDate birthDate) {
		super();
		this.username = username;
		this.id = id;
		this.birthDate = birthDate;
	}
	
	
	public List<Post> getPosts() {
		return posts;
	}


	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", id=" + id + ", birthDate=" + birthDate + "]";
	}
	
	
}
