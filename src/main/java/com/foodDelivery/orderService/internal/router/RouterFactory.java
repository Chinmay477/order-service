package com.foodDelivery.orderService.internal.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.foodDelivery.orderService.constants.OrdersConstants;
import com.foodDelivery.orderService.external.generic.ApiResponse;
import com.foodDelivery.orderService.external.request.OrderPayload;
import com.foodDelivery.orderService.service.PlaceOrderService;

@Component
public class RouterFactory {

    @Autowired
    PlaceOrderService placeOrderService;

    public ResponseEntity<ApiResponse<?>> serviceCall(String routeKey, Object apiRequest) {

        switch (routeKey.toUpperCase()) {

            case OrdersConstants.PLACE_ORDER -> {
                ApiResponse<OrderPayload> response = placeOrderService.placeOrder((OrderPayload) apiRequest);
                return ResponseEntity.status(response.getHttpStatus()).body(response);
            }

            default -> {
                ApiResponse<String> errorResponse = new ApiResponse<>(HttpStatus.BAD_REQUEST, null,
                        "Invalid route key: " + routeKey);
                return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
            }
        }
    }

}
