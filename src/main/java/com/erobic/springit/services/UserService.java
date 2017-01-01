package com.erobic.springit.services;

import com.erobic.springit.entities.Role;
import com.erobic.springit.remote_models.UserRequest;
import com.erobic.springit.exceptions.UsernameExistsException;
import com.erobic.springit.entities.User;
import com.erobic.springit.repositories.UserRepository;
import com.erobic.springit.utils.DateTimeUtil;
import com.erobic.springit.utils.RequestContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author robik
 */
@Service("userService")
public class UserService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(UserService.class.getSimpleName());

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
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
        user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        RequestContextUtil.setUsername(userRequest.getUsername());
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
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                !user.isExpired(),
                !user.isCredentialsExpired(),
                !user.isLocked(),
                getAuthorities(user.getRoles()));
        return userDetails;
    }

    private List<GrantedAuthority> getAuthorities(Collection<Role> roles) {
        if (roles == null) {
            return Collections.EMPTY_LIST;
        } else {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Role role : roles) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
                grantedAuthorities.add(grantedAuthority);
            }
            return grantedAuthorities;
        }
    }
}
