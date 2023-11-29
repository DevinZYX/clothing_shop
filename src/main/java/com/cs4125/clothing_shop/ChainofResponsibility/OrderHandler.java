package com.cs4125.clothing_shop.ChainofResponsibility;

public class OrderHandler implements Handler {
    private Handler nextHandler;

    @Override
    public void setNext(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(CustomerRequest request) {
        if (request.getType().equals("order")) {
            // Processing orders
            System.out.println("Processing order: " + request.getDetails());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}