package com.example.shoppingcart.MongoDb.Repository;

import com.example.shoppingcart.MongoDb.Model.User;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

@Profile("mongodb")
public interface UserRepository extends MongoRepository<User, ObjectId> {
	@Query(value = "{'email': ?0}", fields = "{'name': 1, 'email': 1, 'password': 1, 'role': 1}")
	public Optional<User> findByEmail(String email);


	@Query(value = "{'email': ?0}", fields = "{'name': 1, 'email': 1, 'password': 1, 'cart' : 1}")
	User findUserCart(String email);
	
}
