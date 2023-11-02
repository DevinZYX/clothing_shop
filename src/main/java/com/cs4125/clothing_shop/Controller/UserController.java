package com.cs4125.clothing_shop.Controller;

import com.cs4125.clothing_shop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//commet
@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

}
