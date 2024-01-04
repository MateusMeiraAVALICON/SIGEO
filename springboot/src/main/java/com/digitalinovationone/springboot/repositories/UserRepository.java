package com.digitalinovationone.springboot.repositories;

import com.digitalinovationone.springboot.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

//Defines how the login username will be searched.

//Make a query in the login column from Database based on the parameter (String login) that the client-side sends us
public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByLogin(String login); //Search Based on findBy'Login' column

}
