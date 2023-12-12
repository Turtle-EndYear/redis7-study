package com.redis7_study.controller;

import com.redis7_study.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @ApiOperation("添加订单")
    @PostMapping("/order/add")
    public void addOrder(){
        orderService.addOrder();
    }

    @ApiOperation("根据ID查询订单")
    @GetMapping("/order/{keyId}")
    public String getOrderById(@PathVariable Integer keyId){
        return orderService.getOrderById(keyId);
    }
}
