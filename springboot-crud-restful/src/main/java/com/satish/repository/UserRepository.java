package com.satish.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.satish.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByFirstname(String firstname);
	
	public List<User> findAll();
	
	public Optional<User> findById(Long id);

}
