package com.cs4125.clothing_shop.ChainofResponsibility;

public class CustomerResponse {
    // The 'requestType' field stores the type of the customer request
    private String requestType;

    // The 'message' field holds a response message for the request
    private String message;

    // Constructor
    public CustomerResponse() {
        // Initialization logic if needed
    }

    // Getter for 'requestType'
    public String getRequestType() {
        return requestType;
    }

    // Setter for 'requestType'
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    // Getter for 'message'
    public String getMessage() {
        return message;
    }

    // Setter for 'message'
    public void setMessage(String message) {
        this.message = message;
    }
}