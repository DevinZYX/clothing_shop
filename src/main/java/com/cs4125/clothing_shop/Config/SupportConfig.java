package com.cs4125.clothing_shop.Config;

import com.cs4125.clothing_shop.ChainofResponsibility.BasicInquiryHandler;
import com.cs4125.clothing_shop.ChainofResponsibility.Handler;
import com.cs4125.clothing_shop.ChainofResponsibility.OrderHandler;
import com.cs4125.clothing_shop.ChainofResponsibility.ReturnAndExchangeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SupportConfig {

    @Bean
    public Handler supportHandlerChain() {
        Handler basicInquiryHandler = new BasicInquiryHandler();
        Handler orderHandler = new OrderHandler();
        Handler returnAndExchangeHandler = new ReturnAndExchangeHandler();

        basicInquiryHandler.setNext(orderHandler);
        orderHandler.setNext(returnAndExchangeHandler);

        return basicInquiryHandler;
    }
}
