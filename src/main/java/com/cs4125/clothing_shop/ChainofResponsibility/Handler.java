package com.cs4125.clothing_shop.ChainofResponsibility;

public interface Handler {
    void setNext(Handler nextHandler);
    String handleRequest(CustomerRequest request);

}

