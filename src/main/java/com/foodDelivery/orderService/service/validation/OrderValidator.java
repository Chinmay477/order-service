package com.foodDelivery.orderService.service.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.foodDelivery.orderService.external.request.OrderPayload;
import com.foodDelivery.orderService.external.request.OrderStatus;
import com.foodDelivery.orderService.external.request.PaymentMethod;

@Component
public class OrderValidator {

        private static final Logger logger = LoggerFactory.getLogger(OrderValidator.class);

        public ResponseEntity<String> validateOrderPayload(OrderPayload orderPayload) {

            if (!OrderStatus.isValid(orderPayload.getStatus().toString())) {
                logger.error("Invalid status value: {}",orderPayload.getStatus());
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Invalid status value: " + orderPayload.getStatus());
            }

            if (orderPayload.getUseCaseName().equalsIgnoreCase("PLACE_ORDER") &&
                    !orderPayload.getStatus().toString().equalsIgnoreCase(OrderStatus.NEW_ORDER.name())){

                logger.error("Invalid status: {} for placing new order {}. For new order status should be {}"
                        ,orderPayload.getStatus(),orderPayload.getOrderNo(),OrderStatus.NEW_ORDER.name());
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Invalid status: {} " + orderPayload.getStatus() + " for placing new order "
                                + orderPayload.getOrderNo());
            }

            if (!PaymentMethod.isValid(orderPayload.getPaymentMethod().toString())) {
                logger.error("Invalid payment method value: {}",orderPayload.getPaymentMethod());
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Invalid payment method value: " + orderPayload.getPaymentMethod());
            }


            logger.info("Request is valid for order: {}",orderPayload.getOrderNo());
            return null;
        }
    
}
