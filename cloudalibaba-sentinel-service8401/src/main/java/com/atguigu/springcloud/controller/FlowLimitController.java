package com.atguigu.springcloud.controller;

import ch.qos.logback.core.util.TimeUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController
{

    @GetMapping("/testA")
    public String testA()
    {
//        try {
//            TimeUnit.MILLISECONDS.sleep(1000);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        testC();
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB()
    {
        testC();
        return "------testB";
    }

    @SentinelResource("testC")
    public void testC()
    {
//        try {
//            TimeUnit.MILLISECONDS.sleep(3000);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        System.out.println("*********testC");
    }

    @GetMapping("/testD")
    public String testD()
    {
        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        log.info("testD 测试RT");
        return "------testD";
    }

    @GetMapping("/testE")
    public String testE()
    {
        log.info("testE 测试异常比例");
        int age = 10/0;
        return "------testE 测试异常比例";
    }

    @GetMapping("/testhotkey")
    @SentinelResource(value = "testhotkey", blockHandler = "deal_hotkey")
    public String testhotkey(@RequestParam(value = "p1",required = false)String p1,
                             @RequestParam(value = "p2",required = false)String p2) {
        return "teshotkey 测试成功";
    }

    public String deal_hotkey(String p1, String p2, BlockException be) {
        return "testhotkey 测试失败";
    }


}

