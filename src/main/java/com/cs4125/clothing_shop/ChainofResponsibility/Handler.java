package com.cs4125.clothing_shop.ChainofResponsibility;

public interface Handler {
    void setNext(Handler nextHandler);
    void handleRequest(CustomerRequest request);
}
