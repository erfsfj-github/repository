package com.gcase.annotation;

import com.gcase.IOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @CusReference
    private IOrderService orderService;

    @GetMapping("/test")
    public String test(){
        return orderService.helloWorld();
    }

    @GetMapping("/get")
    public String get(){
        return orderService.orderById("123");
    }


}

