package com.erobic.springit.services;

import com.erobic.springit.remote_models.UserRequest;
import com.erobic.springit.exceptions.UsernameExistsException;
import com.erobic.springit.entities.User;
import com.erobic.springit.repositories.UserRepository;
import com.erobic.springit.utils.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author robik
 */
@Service("userService")
public class UserService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(UserService.class.getSimpleName());

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates user
     *
     * @param userRequest model with details of user to create
     * @return created user
     * @throws UsernameExistsException if user already exists
     */
    public User createUser(UserRequest userRequest) {
        User user = new User();
        verifyUsernameDoesNotExist(userRequest.getUsername());
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setRegisteredOn(DateTimeUtil.nowUTC());
        return userRepository.save(user);
    }

    private void verifyUsernameDoesNotExist(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            throw new UsernameExistsException("username: " + username + " has already been taken");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + s + " was not found");
        }
        return toUserDetails(user);
    }

    private UserDetails toUserDetails(User user) {
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), defaultAuthorities());
        return userDetails;
    }

    private List<GrantedAuthority> defaultAuthorities() {
        GrantedAuthority ga = new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_USER";
            }
        };
        return Arrays.asList(ga);
    }
}
