package com.cs4125.clothing_shop.Controller;


import com.cs4125.clothing_shop.ChainofResponsibility.*;
import com.cs4125.clothing_shop.Dto.CustomerRequestDTO;
import com.cs4125.clothing_shop.Dto.Product.User.SignInDto;
import com.cs4125.clothing_shop.Dto.Product.User.SignInResponseDto;
import com.cs4125.clothing_shop.Dto.Product.User.SignUpResponseDto;
import com.cs4125.clothing_shop.Dto.Product.User.SignupDto;
import com.cs4125.clothing_shop.Model.User.User;
import com.cs4125.clothing_shop.Service.UserService;
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

    public void handleRequest(CustomerRequest request) {
        chain.handleRequest(request);
    }

    public static void main(String[] args) {
        UserController controller = new UserController();
        controller.handleRequest(new CustomerRequest("inquiry", "I have a question about a product."));
        controller.handleRequest(new CustomerRequest("order", "I would like to order a dress."));
        controller.handleRequest(new CustomerRequest("return", "I need to return an item."));
    }

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
    // Endpoint to handle customer requests
    @PostMapping("/customer/request")
    public ResponseEntity<CustomerResponse> handleRequest(@RequestBody CustomerRequestDTO requestDTO) {
        CustomerRequest request = requestDTO.toCustomerRequest();
        userService.handleCustomerRequest(request);

        CustomerResponse response = new CustomerResponse();
        response.setRequestType(request.getType()); // 假设 CustomerRequest 有一个 getType() 方法
        response.setMessage("Request handled");
        return ResponseEntity.ok(response);
    }

}
