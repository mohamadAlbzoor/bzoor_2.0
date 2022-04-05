package com.example.bzoor_second.services;

import com.example.bzoor_second.models.MyUserDetails;
import com.example.bzoor_second.models.User;
import com.example.bzoor_second.repositories.UserRepo;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    final UserRepo userRepository;
    private Optional<User> user;

    public User getUser() {
        return user.get();
    }

    public void setUser(User user) {
        this.user = Optional.of(user);
    }

    public MyUserDetailsService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("MyUserDetailsService -- loading user by user name");
        user = userRepository.findByUserName(username);
        user.orElseThrow(() -> new UsernameNotFoundException(("Not found: " + username)));
        return user.map(MyUserDetails::new).get();
    }
}