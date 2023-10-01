package com.example.shoppingcart.Mysql.Repository;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shoppingcart.Mysql.Model.User;

@Profile("mysql")
public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByEmail(String email);
	Optional<User> findById(int id);
}