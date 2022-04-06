package com.example.bzoor_second.models;

import com.example.bzoor_second.services.MyUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyUserDetails.class);

    private String userName;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(User user){
        this.userName = user.userName();
        this.password = user.password();
        this.active = user.active();
        this.authorities = Arrays.stream(user.roles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        LOGGER.debug("my user details constructor");

    }

    public MyUserDetails(String userName){
        this.userName = userName;
    }

    public MyUserDetails(){
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}