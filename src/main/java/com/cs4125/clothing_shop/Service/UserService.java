package com.cs4125.clothing_shop.Service;

import com.cs4125.clothing_shop.ChainofResponsibility.CustomerRequest;
import com.cs4125.clothing_shop.ChainofResponsibility.Handler;
import com.cs4125.clothing_shop.Config.MessageStrings;
import com.cs4125.clothing_shop.Dto.Product.User.SignInDto;
import com.cs4125.clothing_shop.Dto.Product.User.SignInResponseDto;
import com.cs4125.clothing_shop.Dto.Product.User.SignUpResponseDto;
import com.cs4125.clothing_shop.Dto.Product.User.SignupDto;
import com.cs4125.clothing_shop.Model.AuthenticationToken;
import com.cs4125.clothing_shop.Model.User.User;
import com.cs4125.clothing_shop.Repository.UserRepo;
import com.exceptions.AuthenticationFailException;
import com.exceptions.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepo userRepository;

    @Autowired
    AuthenticationService authenticationService;

    private final Handler supportChain;

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

    // Constructor injection of the support chain
    @Autowired
    public UserService(Handler supportChain) {
        this.supportChain = supportChain;
    }
    public String handleCustomerRequest(CustomerRequest request) {
        return supportChain.handleRequest(request);
    }

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public SignUpResponseDto signUp(SignupDto signupDto)  throws CustomException {
        // Check to see if the current email address has already been registered.
        if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
            // If the email address has been registered then throw an exception.
            throw new CustomException("User already exists");
        }
        // first encrypt the password
        String encryptedPassword = signupDto.getPassword();
        try {
            encryptedPassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
        }

        User user = new User(signupDto.getFirstName(), signupDto.getLastName(), signupDto.getEmail(), encryptedPassword);
        try {
            // save the User
            userRepository.save(user);
            // generate token for user
            final AuthenticationToken authenticationToken = new AuthenticationToken(user);
            // save token in database
            authenticationService.saveConfirmationToken(authenticationToken);
            // success in creating
            return new SignUpResponseDto("success", "user created successfully");
        } catch (Exception e) {
            // handle signup error
            throw new CustomException(e.getMessage());
        }
    }

    String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;
    }

    public SignInResponseDto signIn(SignInDto signInDto) throws AuthenticationFailException, CustomException {
        // first find User by email
        User user = userRepository.findByEmail(signInDto.getEmail());
        if(!Objects.nonNull(user)){
            throw new AuthenticationFailException("user not present");
        }
        try {
            // check if password is right
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))){
                // passwords do not match
                throw  new AuthenticationFailException(MessageStrings.WRONG_PASSWORD);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
            throw new CustomException(e.getMessage());
        }

        AuthenticationToken token = authenticationService.getToken(user);

        if(!Objects.nonNull(token)) {
            // token not present
            throw new CustomException(MessageStrings.AUTH_TOEKN_NOT_PRESENT);
        }

        return new SignInResponseDto ("success", token.getToken());
    }
}
