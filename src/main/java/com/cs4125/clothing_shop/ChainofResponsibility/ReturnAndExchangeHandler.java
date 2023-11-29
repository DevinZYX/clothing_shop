package com.cs4125.clothing_shop.ChainofResponsibility;

public class ReturnAndExchangeHandler implements Handler {
    private Handler nextHandler;

    @Override
    public void setNext(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public String handleRequest(CustomerRequest request) {
        if ("return".equals(request.getType())) {
            String response = "Handling return/exchange: " + request.getDetails();
            System.out.println(response); // You can also use a logger here
            return response;
        } else if (nextHandler != null) {
            return nextHandler.handleRequest(request);
        }
        return "Return request type not handled.";
    }
}
