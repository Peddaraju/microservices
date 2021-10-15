package com.app.rest.controller;

import com.app.rest.model.User;
import com.app.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> retrieveAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User retriveUser(@PathVariable int id) {
        return userService.getUser(id);
    }


    @PostMapping("/users")
    public User saveUser(@RequestBody User user) {
        return userService.saveUsers(user);
    }


}
