package com.madhu.socialmedia.Instagram.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madhu.socialmedia.Instagram.user.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
