package com.satish.controller;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satish.entity.User;
import com.satish.exception.ResourceNotFoundException;
import com.satish.repository.UserRepository;
import com.satish.services.IUserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private IUserService userService;
	

	//get All users
	@GetMapping
	public List<User> getAllUsers(){
		return userService.findAll();
	}

	//get user By Id
	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable(value = "id")long id) {
		return userService.getUserById(id);
				
	}
	//get user By firstname
	@GetMapping("/name/{firstname}")
	public ResponseEntity<?> findByFirstname(@PathVariable(value = "firstname")String firstname) {
		User user = userRepository.findByFirstname(firstname);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	//create User
	@PostMapping()
	public User createUser(@RequestBody User user) {
		return this.userRepository.save(user);
	}

	//update user
	@PutMapping("/{id}")
	public User updateUser(@PathVariable(value = "id")long id,@RequestBody User user) {
		User exsitingUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User Id is not found: "+id));
		return this.userRepository.save(exsitingUser);
	}

	//delete user by Id
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUserById(@PathVariable(value = "id")long id){
		User exsitingUser = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User Id is not found"+id));
		this.userRepository.delete(exsitingUser);
		return ResponseEntity.ok().build();
	}

	public static boolean isValidUsername(String name) 
	{ 
		// Regex to check valid username. 
		String regex = "^[aA-zZ]\\w{5,29}$"; 

		// Compile the ReGex 
		Pattern p = Pattern.compile(regex); 

		// If the username is empty 
		// return false 
		if (name == null) { 
			return false; 
		} 

		// Pattern class contains matcher() method 
		// to find matching between given username 
		// and regular expression. 
		Matcher m = p.matcher(name); 

		// Return if the username 
		// matched the ReGex 
		return m.matches(); 
	} 
}

