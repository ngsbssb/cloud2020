package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService  {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "服务宕机，请稍后再试";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "服务宕机，请稍后再试";
    }
}
