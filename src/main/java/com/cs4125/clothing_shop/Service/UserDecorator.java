package com.cs4125.clothing_shop.Service;

import com.cs4125.clothing_shop.Model.User.User;
import org.springframework.beans.factory.annotation.Autowired;

import com.cs4125.clothing_shop.Model.User.User;

public abstract class UserDecorator extends User {
    @Autowired
    private User decoratedUser;

    public UserDecorator(User decoratedUser){
        this.decoratedUser = decoratedUser;
    }

//    @Override
//    public double getDiscount(){
//        if(decoratedUser.getLevel().equals(UserLevel.BRONZE_USER)){
//            return 0.95;
//        }
//    }


}
