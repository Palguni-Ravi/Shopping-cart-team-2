package com.example.shoppingcart.MongoDb.Repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import com.example.shoppingcart.MongoDb.Model.User;

class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;

@Test
public void testSaveUser() {
    User user = new User();
    user.setName("John");
    user.setEmail("john@example.com");
    user.setPassword("password");

    User savedUser = userRepository.save(user);

    assertEquals(user.getName(), savedUser.getName());
    assertEquals(user.getEmail(), savedUser.getEmail());
    assertEquals(user.getPassword(), savedUser.getPassword());

   
}

}
