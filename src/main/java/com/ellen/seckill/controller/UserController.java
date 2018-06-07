package com.ellen.seckill.controller;

import com.ellen.seckill.domain.Result;
import com.ellen.seckill.domain.User;
import com.ellen.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user")
    public Result userNew (User user) {
        return userService.register(user);
    }

}
