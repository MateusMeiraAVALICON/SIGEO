package com.digitalinovationone.springboot.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String login;
    private String password;
    private UserRole role; //Based on the group the user belongs to, defines its numbering which comes from the UserRole class

    public User(String login, String password, UserRole role){
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if(this.role == UserRole.ADMIN) { //Checks the group the user belongs to and instantiates their permissions in a hierarchy format.
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }

    }

    @Override
    public String getUsername() {
        return login;
    } //Receives login username from user

    @Override
    public boolean isAccountNonExpired() {
        return true;
    } // Definido como true temporariamente

    @Override
    public boolean isAccountNonLocked() {
        return true;
    } // Definido como true temporariamente

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    } // Definido como true temporariamente

    @Override
    public boolean isEnabled() {
        return true;
    } // Definido como true temporariamente
}