package com.madhu.socialmedia.Instagram.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	static List<User> users = new ArrayList<User>();
	private static int count = 0;
	static {
		users.add(new User("Madhu", ++count, LocalDate.now().minusYears(30)));
		users.add(new User("Sri", ++count, LocalDate.now().minusYears(40)));
		users.add(new User("Agan", ++count, LocalDate.now().minusYears(20)));
	}
	
	public List<User> findAll(){
		return users;
	}

	public User retrieveByID(int id) {
		
		Predicate<? super User> predicate=users-> users.getId() == id;
		return users.stream().filter(predicate).findFirst().orElse(null);
	}

	public User save(User user) {
		user.setId(++count);
		users.add(user);
		return user;
		
	}

	public void delete(int id) {
		User user=retrieveByID(id);
		users.remove(user);
		
	}
}
