package com.example.shoppingcart.Mysql.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppingcart.Mysql.Model.User;
import com.example.shoppingcart.Mysql.Repository.UserRepository;
import com.example.shoppingcart.Mysql.Service.SuperadminAssignmentService;

@RestController
@RequestMapping("/api/superadmin")
public class SuperadminController {

    @Autowired
    private SuperadminAssignmentService superadminAssignmentService;

    @Autowired 
    private UserRepository userRepository;

    @PostMapping("/assign-superadmin")
    public ResponseEntity<String> assignSuperadminRole(
            @RequestParam("currentSuperadminId") int currentSuperadminId,
            @RequestParam("targetUserId") int targetUserId) {

        User currentSuperadmin = userRepository.findById(currentSuperadminId).orElse(null);
        User targetUser = userRepository.findById(targetUserId).orElse(null);

        if (currentSuperadmin != null && targetUser != null) {
            boolean success = superadminAssignmentService.assignSuperadminRole(currentSuperadmin, targetUser);

            if (success) {
                return ResponseEntity.ok("Superadmin role assigned successfully.");
            } else {
                return ResponseEntity.badRequest().body("Failed to assign superadmin role.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


