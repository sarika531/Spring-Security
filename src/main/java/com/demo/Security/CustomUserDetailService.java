package com.demo.Security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.Entity.User;
import com.demo.Exception.UserNotFound;
import com.demo.Repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Fetch user from the repository
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFound(String.format("User with email: %s not found", email))
        );

        // Convert custom User entity to Spring Security User object
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                getAuthorities(user)
        );
    }

    private List<SimpleGrantedAuthority> getAuthorities(User user) {
        // Assuming that roles are hardcoded or mapped as a list of strings
        return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
