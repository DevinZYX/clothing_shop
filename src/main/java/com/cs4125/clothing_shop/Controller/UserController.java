package com.cs4125.clothing_shop.Controller;


import com.cs4125.clothing_shop.ChainofResponsibility.*;
import com.cs4125.clothing_shop.Dto.CustomerRequestDTO;
import com.cs4125.clothing_shop.Dto.Product.User.SignInDto;
import com.cs4125.clothing_shop.Dto.Product.User.SignInResponseDto;
import com.cs4125.clothing_shop.Dto.Product.User.SignUpResponseDto;
import com.cs4125.clothing_shop.Dto.Product.User.SignupDto;
import com.cs4125.clothing_shop.Model.User.User;
import com.cs4125.clothing_shop.Service.UserService;
import com.exceptions.AuthenticationFailException;
import com.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    // Constructor injection of UserService
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    private Handler chain;

    public UserController() {
        // Building a Chain of Responsibility
        buildChain();
    }

    private void buildChain() {
        this.chain = new BasicInquiryHandler();
        Handler orderHandler = new OrderHandler();
        Handler returnHandler = new ReturnAndExchangeHandler();
        chain.setNext(orderHandler);
        orderHandler.setNext(returnHandler);
    }

    @PostMapping("/signup")
    public SignUpResponseDto Signup(@RequestBody SignupDto signupDto) throws CustomException {
        return userService.signUp(signupDto);
    }

    @PostMapping("/signIn")
    public SignInResponseDto SignIn(@RequestBody SignInDto signInDto) throws CustomException, AuthenticationFailException {
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
    // Endpoint to handle customer requests
    @PostMapping("/customer/request")
    public ResponseEntity<String> handleRequest(@RequestBody CustomerRequestDTO requestDTO) {
        CustomerRequest request = requestDTO.toCustomerRequest();
        String response = userService.handleCustomerRequest(request);
        return ResponseEntity.ok(response);
    }

}
