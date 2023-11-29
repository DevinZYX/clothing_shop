package com.cs4125.clothing_shop.ChainofResponsibility;

// CustomerRequest.java
public class CustomerRequest {
    private String type; // Types such as "order", "return", "inquiry"
    private String details; // Request details


    // Constructors, getters and setters
    public CustomerRequest(String type, String details) {
        this.type = type;
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}
