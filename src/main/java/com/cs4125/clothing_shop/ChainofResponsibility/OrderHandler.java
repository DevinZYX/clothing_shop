package com.cs4125.clothing_shop.ChainofResponsibility;

public class OrderHandler implements Handler {
    private Handler nextHandler;

    @Override
    public void setNext(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public String handleRequest(CustomerRequest request) {
        if ("order".equals(request.getType())) {
            String response = "Processing order: " + request.getDetails();
            System.out.println(response); // You can also use a logger here
            return response;
        } else if (nextHandler != null) {
            return nextHandler.handleRequest(request);
        }
        return "Order request type not handled.";
    }
}