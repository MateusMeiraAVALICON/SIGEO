package com.digitalinovationone.springboot.controller;

import com.digitalinovationone.springboot.domain.user.AuthenticationDTO;
import com.digitalinovationone.springboot.domain.user.LoginResponseDTO;
import com.digitalinovationone.springboot.domain.user.RegisterDTO;
import com.digitalinovationone.springboot.domain.user.User;
import com.digitalinovationone.springboot.repositories.UserRepository;
import com.digitalinovationone.springboot.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build(); //Search the database in the login column, if it finds a similar record, it returns an error

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password()); //Encrypt password with Bcrypt
        User newUser = new User(data.login(), encryptedPassword, data.role()); //Saves the login data, encrypted password and his group number in the newUser object

        this.repository.save(newUser); //Performs user insertion based on object

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

}
