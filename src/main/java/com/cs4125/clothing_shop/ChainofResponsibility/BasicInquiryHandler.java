package com.cs4125.clothing_shop.ChainofResponsibility;


// BasicInquiryHandler.java
public class BasicInquiryHandler implements Handler {
    private Handler nextHandler;

    @Override
    public void setNext(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public String handleRequest(CustomerRequest request) {
        if ("inquiry".equals(request.getType())) {
            String response = "Handling basic inquiry: " + request.getDetails();
            System.out.println(response); // or log it
            return response;
        } else if (nextHandler != null) {
            return nextHandler.handleRequest(request);
        }
        return "Request type not supported.";
    }
}
