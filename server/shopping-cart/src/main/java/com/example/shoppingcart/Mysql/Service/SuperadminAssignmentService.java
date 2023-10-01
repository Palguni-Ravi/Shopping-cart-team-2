package com.example.shoppingcart.Mysql.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.shoppingcart.Mysql.Model.User;
import com.example.shoppingcart.Mysql.Repository.UserRepository;

@Service
@Profile("mysql")
public class SuperadminAssignmentService {

    @Autowired
    private UserRepository userRepository; // Inject the UserRepository

    public boolean assignSuperadminRole(User currentSuperadmin, User targetUser) {
        if (currentSuperadmin.isSuperadmin()) {
            // Revoke superadmin role from current superadmin
            currentSuperadmin.setSuperadmin(false);
            userRepository.save(currentSuperadmin);

            // Assign superadmin role to the target user
            targetUser.setSuperadmin(true);
            userRepository.save(targetUser);

            return true; // Successful role assignment
        }
        return false; // Current user is not a superadmin
    }
}

