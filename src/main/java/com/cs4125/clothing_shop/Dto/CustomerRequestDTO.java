package com.cs4125.clothing_shop.Dto;

import com.cs4125.clothing_shop.ChainofResponsibility.CustomerRequest;

public class CustomerRequestDTO {
    private String type; // Request type, e.g., "order", "return", "inquiry"
    private String details; // Details of the request

    // Constructor, getters, and setters
    public CustomerRequestDTO(String type, String details) {
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

    // Converts this DTO to the internal CustomerRequest object
    public CustomerRequest toCustomerRequest() {
        return new CustomerRequest(this.type, this.details);
    }
}