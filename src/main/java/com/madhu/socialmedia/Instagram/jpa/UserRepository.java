package com.madhu.socialmedia.Instagram.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madhu.socialmedia.Instagram.user.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
