package com.example.shoppingcart.MongoDb.Service;

import com.example.shoppingcart.MongoDb.Model.User;
import com.example.shoppingcart.MongoDb.Repository.UserRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Profile("mongodb")
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public User createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	public Optional<User> getUserRoleByEmail(String email) {
	    return userRepository.findByEmail(email);
	}
}
