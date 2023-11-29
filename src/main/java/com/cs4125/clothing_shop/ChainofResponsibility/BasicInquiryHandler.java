package com.cs4125.clothing_shop.ChainofResponsibility;


// BasicInquiryHandler.java
public class BasicInquiryHandler implements Handler {
    private Handler nextHandler;

    @Override
    public void setNext(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(CustomerRequest request) {
        if (request.getType().equals("inquiry")) {
            // Handling of inquiries
            System.out.println("Handling basic inquiry: " + request.getDetails());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}
