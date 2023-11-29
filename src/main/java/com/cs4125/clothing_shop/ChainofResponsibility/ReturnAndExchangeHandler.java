package com.cs4125.clothing_shop.ChainofResponsibility;

public class ReturnAndExchangeHandler implements Handler {
    private Handler nextHandler;

    @Override
    public void setNext(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(CustomerRequest request) {
        if (request.getType().equals("return")) {
            // Processing of returns/exchanges
            System.out.println("Handling return/exchange: " + request.getDetails());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}
