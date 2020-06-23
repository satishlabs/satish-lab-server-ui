package com.satish.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.satish.entity.User;

@Component
public class UserCache {
	private Map<Long, User> userCache = new HashMap<Long, User>();
	
	public User lookUp(Long userId) {
		User user = this.userCache.get(userId);
		return user;
	}
	
	public void buildCache(List<User> userList){
		for (User user : userList) {
			userCache.put(user.getId(), user);
		}
	}
	
	
}
