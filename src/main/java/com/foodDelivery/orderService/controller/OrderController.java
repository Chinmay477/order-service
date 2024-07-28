package com.foodDelivery.orderService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodDelivery.orderService.external.request.OrderPayload;
import com.foodDelivery.orderService.internal.router.RouterFactory;

import jakarta.validation.Valid;
import lombok.SneakyThrows;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    RouterFactory routerFactory;

    @SneakyThrows
    @PostMapping("/place-order")
    public ResponseEntity<?> placeOrder(@Valid @RequestBody OrderPayload orderRequest) {

        return routerFactory.serviceCall(orderRequest.getUseCaseName(), orderRequest);
    }

}