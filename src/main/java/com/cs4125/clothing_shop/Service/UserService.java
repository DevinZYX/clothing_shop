package com.cs4125.clothing_shop.Service;

import com.cs4125.clothing_shop.Dto.Product.User.SignInDto;
import com.cs4125.clothing_shop.Dto.Product.User.SignInResponseDto;
import com.cs4125.clothing_shop.Dto.Product.User.SignUpResponseDto;
import com.cs4125.clothing_shop.Dto.Product.User.SignupDto;
import com.cs4125.clothing_shop.Model.User.User;
import com.cs4125.clothing_shop.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepository;

    //signup
    public SignUpResponseDto signUp(SignupDto signupDto){
        return null;
    }
    //signIn
    public SignInResponseDto signIn(SignInDto signinDto){
        return null;
    }

    public User addPurchase(SignInDto signInDto, double amount) {
        User user = userRepository.findByEmail(signInDto.getEmail());
        if (user != null) {
            UserDecorator decoratedUser = decorateUser(user);
            decoratedUser.addPurchaseAmount(amount);
            userRepository.save(user);
        }
        return user;
    }

    private UserDecorator decorateUser(User user) {
        return switch (user.getLevel()) {
            case "Gold" -> new UserDecorator.GoldMemberDecorator(user);
            case "Silver" -> new UserDecorator.SilverMemberDecorator(user);
            default -> new UserDecorator.RegularMemberDecorator(user);
        };
    }
}
