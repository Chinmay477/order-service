package com.foodDelivery.orderService.controller;

import com.foodDelivery.orderService.external.request.OrderPayload;
import com.foodDelivery.orderService.internal.router.RouterFactory;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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