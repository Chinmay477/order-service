package com.foodDelivery.orderService.controller;

import com.foodDelivery.orderService.external.generic.ApiResponse;
import com.foodDelivery.orderService.external.request.OrderPayload;
import com.foodDelivery.orderService.service.facade.OrderFacade;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderFacade orderFacade;

    @SneakyThrows
    @PostMapping("/place-order")
    public ApiResponse<OrderPayload> placeOrder(@Valid @RequestBody OrderPayload orderRequest) {
        return orderFacade.initiateOrder(orderRequest);
    }

}