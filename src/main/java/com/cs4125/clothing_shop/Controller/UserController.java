package com.cs4125.clothing_shop.Controller;

import com.cs4125.clothing_shop.Dto.Product.User.SignInDto;
import com.cs4125.clothing_shop.Dto.Product.User.SignInResponseDto;
import com.cs4125.clothing_shop.Dto.Product.User.SignUpResponseDto;
import com.cs4125.clothing_shop.Dto.Product.User.SignupDto;
import com.cs4125.clothing_shop.Model.User.User;
import com.cs4125.clothing_shop.Service.UserService;
import com.exceptions.AuthenticationFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public SignUpResponseDto Signup(@RequestBody SignupDto signupDto) {
        return userService.signUp(signupDto);
    }

    @PostMapping("/signIn")
    public SignInResponseDto SignIn(@RequestBody SignInDto signInDto) {
        return userService.signIn(signInDto);
    }

    @PostMapping("/purchase")
    public ResponseEntity<User> addPurchase(@RequestBody SignInDto signInDto, @RequestParam double amount) {
        User user = userService.addPurchase(signInDto, amount);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

}
