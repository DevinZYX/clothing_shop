package com.cs4125.clothing_shop.Model.User;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "level")
    private String level;

    @Column(name = "purchase_amount")
    private double purchaseAmount;



    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.level = level;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void addPurchaseAmount(double amount) {
        this.purchaseAmount += amount;
        upgradeMembershipIfNeeded();
    }

    public String upgradeMembershipIfNeeded() {
        if ("normal".equals(this.level) && this.purchaseAmount >= 10000) {
            this.level = "silver";
            return "silver";
        } else if ("silver".equals(this.level) && this.purchaseAmount >= 50000) {
            this.level = "gold";
            return "gold";
        }
        return this.level;
    }
    public double getDiscount() {
        return switch (this.level) {
            case "silver" -> 0.90; //10% discount for silver members
            case "gold" -> 0.80; //20% discount for gold members
            default -> 0.95; //5% discount for normal members
        };
    }
    
    public double getBonusForGoldMember() {
        if ("gold".equals(this.level)) {
            return Math.floor(this.purchaseAmount / 1000) * 10; // 10 unit bonus for every 1000 units spent
        }
        return 0;
    } 
}
