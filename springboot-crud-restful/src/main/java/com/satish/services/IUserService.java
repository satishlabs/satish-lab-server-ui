package com.satish.services;

import java.util.List;
import java.util.Optional;

import com.satish.entity.User;

public interface IUserService {
	public Optional<User> getUserById(long id) ;

	public List<User> findAll();
}
