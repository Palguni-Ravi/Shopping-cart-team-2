package com.example.shoppingcart.Controller;

import com.example.shoppingcart.Model.JwtResponse;
import com.example.shoppingcart.Model.UserModel;
import com.example.shoppingcart.Repository.UserRepository;
import com.example.shoppingcart.Service.UserService;
import com.example.shoppingcart.sercurity.JwtHelper;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserService userService;

  @GetMapping("/{email}")
  public boolean getUserDetail(@PathVariable String email) {
    UserModel user = userRepository.findByEmail(email).orElseThrow();
    if (user != null) {
      return true;
    } else {
      return false;
    }
  }

  @GetMapping("/{email}/{password}")
  public ResponseEntity<JwtResponse> validatePassword(
    @PathVariable String email,
    @PathVariable String password
  ) {
    UserModel user = userRepository.findByEmail(email).orElseThrow();
    boolean isPasswordCorrect = passwordEncoder.matches(
      password,
      user.getPassword()
    );
    if (isPasswordCorrect) {
      UserDetails userDetails = userDetailsService.loadUserByUsername(email);
      String token = this.helper.generateToken(userDetails);
      JwtResponse response = JwtResponse
        .builder()
        .jwtToken(token)
        .email(userDetails.getUsername())
        .build();
      return new ResponseEntity<>(response, HttpStatus.OK);
    }
    return null;
  }

  @PostMapping("/createUser")
  public ResponseEntity<JwtResponse> createUser(@RequestBody UserModel user) {
    if (user.getCartItem() == null) {
      user.setCartItem(new ArrayList<>());
    }

    UserModel userModel = userService.createUser(user);
    UserDetails userDetails = userDetailsService.loadUserByUsername(
      userModel.getEmail()
    );

    String token = this.helper.generateToken(userDetails);
    JwtResponse response = JwtResponse
      .builder()
      .jwtToken(token)
      .email(userDetails.getUsername())
      .build();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private AuthenticationManager manager;

  @Autowired
  private JwtHelper helper;

  private Logger logger = LoggerFactory.getLogger(AuthController.class);

  @PostMapping("/login")
  public ResponseEntity<JwtResponse> login(@RequestBody UserModel request) {
    this.doAuthenticate(request.getEmail(), request.getPassword());

    UserDetails userDetails = userDetailsService.loadUserByUsername(
      request.getEmail()
    );
    String token = this.helper.generateToken(userDetails);

    JwtResponse response = JwtResponse
      .builder()
      .jwtToken(token)
      .email(userDetails.getUsername())
      .build();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  private void doAuthenticate(String email, String password) {
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
      email,
      password
    );
    try {
      manager.authenticate(authentication);
    } catch (BadCredentialsException e) {
      throw new BadCredentialsException(" Invalid Username or Password  !!");
    }
  }

  @ExceptionHandler(BadCredentialsException.class)
  public String exceptionHandler() {
    return "Credentials Invalid !!";
  }
}
