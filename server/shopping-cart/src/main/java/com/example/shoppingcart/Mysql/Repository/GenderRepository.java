package com.example.shoppingcart.Mysql.Repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.shoppingcart.Mysql.Model.Gender;

@Profile("mysql")
public interface GenderRepository extends JpaRepository<Gender , Integer>{

	Gender findByName(String genderName);

}
