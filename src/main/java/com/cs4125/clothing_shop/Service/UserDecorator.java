package com.cs4125.clothing_shop.Service;

import com.cs4125.clothing_shop.Model.User.User;

public abstract class UserDecorator extends User {
    protected User decoratedUser;

    public UserDecorator(User decoratedUser) {
        this.decoratedUser = decoratedUser;
    }

    protected User getDecoratedUser() {
        return decoratedUser;
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
    public static class RegularMemberDecorator extends UserDecorator {
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
                getDecoratedUser().setLevel("Silver");
                return "Silver";
            }
            return "Regular";
        }
    }

    //SilverMember
    public static class SilverMemberDecorator extends UserDecorator {
        private double totalPurchaseAmount = 0;

        public SilverMemberDecorator(User decoratedUser) {
            super(decoratedUser);
        }

        @Override
        public double getDiscount() {
            return super.getDiscount() * 0.9;
        }

        @Override
        public void addPurchaseAmount(double amount) {
            totalPurchaseAmount += amount;
        }

        @Override
        public String upgradeMembershipIfNeeded() {
            if (totalPurchaseAmount >= 50000) {
                decoratedUser.setLevel("Gold");
                getDecoratedUser().setLevel("Gold");
                return "Gold";
            }
            return "Silver";
        }
    }

    //GoldMember
    public static class GoldMemberDecorator extends UserDecorator {
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