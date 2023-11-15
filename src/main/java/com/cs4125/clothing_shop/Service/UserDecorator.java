package com.cs4125.clothing_shop.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cs4125.clothing_shop.Model.User.User;

public abstract class UserDecorator extends User {
    @Autowired
    private User decoratedUser;

    public UserDecorator(User decoratedUser){
        this.decoratedUser = decoratedUser;
    }
    @Override
    public Integer getId() {
        return decoratedUser.getId();
    }
    @Override
    public String getLevel() {
        return decoratedUser.getLevel();
    }
    
    
    //RegularMember
    public class RegularMemberDecorator extends UserDecorator {
        private double totalPurchaseAmount = 0;
    
        public RegularMemberDecorator(User decoratedUser) {
            super(decoratedUser);
        }
    
        @Override
        public double getDiscount() {
            return super.getDiscount() * 0.95; 
        }
    
        @Override
        public void addPurchaseAmount(double amount) {
            totalPurchaseAmount += amount;
        }
    
        @Override
        public String upgradeMembershipIfNeeded() {
            if (totalPurchaseAmount >= 10000) {
                decoratedUser.setLevel("Silver");
                return "Silver";
            }
            return "Regular";
        }
    }
    
    //SilverMember
    public class SilverMemberDecorator extends UserDecorator {
        private double totalPurchaseAmount = 0;
    
        public SilverMemberDecorator(User decoratedUser) {
            super(decoratedUser);
        }
    
        @Override
        public double getDiscount() {
            return super.getDiscount() * 0.9; // 9折优惠
        }
    
        @Override
        public void addPurchaseAmount(double amount) {
            totalPurchaseAmount += amount;
        }
    
        @Override
        public String upgradeMembershipIfNeeded() {
            if (totalPurchaseAmount >= 50000) {
                decoratedUser.setLevel("Gold");
                return "Gold";
            }
            return "Silver";
        }
    }
    
    //GoldMember
    public class GoldMemberDecorator extends UserDecorator {
        private double couponAmount = 0;
    
        public GoldMemberDecorator(User decoratedUser) {
            super(decoratedUser);
        }
    
        @Override
        public double getDiscount() {
            return super.getDiscount() * 0.8;
        }
    
        @Override
        public void addPurchaseAmount(double amount) {
            couponAmount += (int) (amount / 1000) * 10;
        }
    
        @Override
        public String upgradeMembershipIfNeeded() {
            //No need to upgraded
            return "Gold";
        }
    
        public double getCouponAmount() {
            return couponAmount;
        }
    }
}
