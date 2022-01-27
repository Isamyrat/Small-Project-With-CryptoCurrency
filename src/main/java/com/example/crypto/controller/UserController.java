package com.example.crypto.controller;

import com.example.crypto.model.User;
import com.example.crypto.model.UserCoin;
import com.example.crypto.service.UserCoinService;
import com.example.crypto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserCoinService userCoinService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/getAllCoins", method = RequestMethod.GET)
    public List<UserCoin> getAllUsersCoins() {
        return userCoinService.getAllUserCoins();
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public void addUser(@RequestBody User user, @RequestParam(required=false) String symbol) {
        userService.addUser(user,symbol);
    }
}
