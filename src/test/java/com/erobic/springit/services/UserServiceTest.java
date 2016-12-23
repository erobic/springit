package com.erobic.springit.services;

import com.erobic.springit.dtos.UserRequest;
import com.erobic.springit.exceptions.UsernameExistsException;
import com.erobic.springit.generator.DataGenerator;
import com.erobic.springit.models.User;
import com.erobic.springit.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;

    @Test
    public void createUserSuccessfully() throws Exception {
        //given
        String username = "user123";
        when(userRepository.findByUsername(eq(username))).thenReturn(null);
        UserRequest userRequest = DataGenerator.generateUserRequest();
        userRequest.setUsername(username);
        doAnswer(AdditionalAnswers.returnsFirstArg()).when(userRepository).save(any(User.class));
        //when
        User user = userService.createUser(userRequest);
        //then
        assertThat(user.getUsername()).isEqualTo(username);
    }

    @Test(expected = UsernameExistsException.class)
    public void createUserThrowsUsernameExists() {
        //given
        String username = "user123";
        User user = DataGenerator.generateUser();
        when(userRepository.findByUsername(eq(username))).thenReturn(user);
        UserRequest userRequest = DataGenerator.generateUserRequest();
        userRequest.setUsername(username);
        //when
        userService.createUser(userRequest);
    }

    @Test
    public void loadUserByUsernameSuccessfully() throws Exception {
        //given
        String username = "username123";
        User user = DataGenerator.generateUser();
        when(userRepository.findByUsername(eq(username))).thenReturn(user);
        //when
        UserDetails loadedUser = userService.loadUserByUsername(username);
        assertThat(loadedUser).isNotNull();
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsernameWithNonExistingUsername() throws Exception {
        //given
        String username = "username123";
        when(userRepository.findByUsername(eq(username))).thenReturn(null);
        //when
        userService.loadUserByUsername(username);
    }
}