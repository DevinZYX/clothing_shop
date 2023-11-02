package com.cs4125.clothing_shop.Service;

import org.springframework.beans.factory.annotation.Autowired;

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
