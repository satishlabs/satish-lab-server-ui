package com.satish.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.satish.entity.User;
import com.satish.repository.UserCache;
import com.satish.repository.UserRepository;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserCache userCache;
	
	public UserService() {
		System.out.println("UserService construcotr..........*******************");
	}
	
	
	public static void loadUsers() {
		System.out.println("loadUsers() called..%%%%%%%%%%%%%%%%%%%%%555.");
	
	}
	
	@Override	
	public Optional<User> getUserById(long id) {
		User user = userCache.lookUp(id);
		if(user == null) {
		  System.out.println("User is not found in cache");
		  Optional<User> user1 = userRepository.findById(id);
		  return user1;
		}else {
			return Optional.ofNullable(user);
		}
	}
	
	 @EventListener
	    public void onApplicationReady(ApplicationReadyEvent ready) {
	       System.out.println("@EventListener %%%%%%%%%%%%%%%%%%%##################################3");
	       
	       List<User> usrList =  userRepository.findAll();
	       
	       userCache.buildCache(usrList);
	    }


	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
}
