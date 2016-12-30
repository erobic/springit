package com.erobic.springit.controllers;

import com.erobic.springit.remote_models.CreatedResponse;
import com.erobic.springit.remote_models.UserRequest;
import com.erobic.springit.entities.User;
import com.erobic.springit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by robik on 12/23/16.
 */
@RestController
@RequestMapping("/users")
public class UserController extends AbstractController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CreatedResponse> createUser(@RequestBody UserRequest userRequest) {
        User user = userService.createUser(userRequest);
        return created(user.getId());
    }
}
