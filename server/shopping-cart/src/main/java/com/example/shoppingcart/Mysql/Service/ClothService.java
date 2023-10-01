package com.example.shoppingcart.Mysql.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.shoppingcart.Mysql.Model.Cloth;
import com.example.shoppingcart.Mysql.Repository.ClothRepository;

@Service
@Profile("mysql")
public class ClothService {

    @Autowired
    private ClothRepository clothRepository;

    public Cloth addCloth(Cloth cloth) {
        // Perform any validation or business logic here
        return clothRepository.save(cloth);
    }

    public Cloth updateCloth(int id, Cloth updatedCloth) {
        // Find the existing cloth by ID
        Cloth existingCloth = clothRepository.findById(id).orElse(null);

        if (existingCloth != null) {
            // Update the properties of the existing cloth
            existingCloth.setTag(updatedCloth.getTag());
            existingCloth.setImage(updatedCloth.getImage());
            existingCloth.setGender(updatedCloth.getGender());
            existingCloth.setColor(updatedCloth.getColor());
            existingCloth.setRating(updatedCloth.getRating());
            existingCloth.setPrice(updatedCloth.getPrice());
            existingCloth.setBrand(updatedCloth.getBrand());
            existingCloth.setCategory(updatedCloth.getCategory());
            existingCloth.setPincode(updatedCloth.getPincode());

            // Perform any other validation or business logic here

            // Save the updated cloth
            return clothRepository.save(existingCloth);
        } else {
            // Handle the case where the cloth with the given ID is not found
            return null;
        }
    }
}

