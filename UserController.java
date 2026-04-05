package com.finance.financedata.controller;

import com.finance.financedata.model.User;
import com.finance.financedata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    // Create the user according to the given type.
    @PostMapping
    public User create(@RequestBody User user) {
        return service.saveUser(user);
    }

    // It will give all the users
    @GetMapping
    public List<User> getAll() {
        return service.getAllUsers();
    }
}