package com.foodDelivery.orderService.service;

import com.foodDelivery.orderService.exception.UserNotFoundException;
import com.foodDelivery.orderService.external.bo.OrderDetailsBo;
import com.foodDelivery.orderService.external.generic.ApiResponse;
import com.foodDelivery.orderService.external.request.OrderPayload;
import com.foodDelivery.orderService.internal.adapters.OrderDetailsMapper;
import com.foodDelivery.orderService.internal.entity.OrdersT;
import com.foodDelivery.orderService.service.internal.OrderPersistenceService;
import com.foodDelivery.orderService.service.validation.OrderValidator;
import com.foodDelivery.orderService.service.validation.ValidUserValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CreateOrderService {

    @Autowired
    OrderValidator orderValidator;
    @Autowired
    KafkaMessagePublisher kafkaMessagePublisher;
    @Autowired
    ValidUserValidation userValidation;
    @Autowired
    OrderPersistenceService orderPersistence;

    private static final Logger logger = LoggerFactory.getLogger(CreateOrderService.class);

    
    public ApiResponse<OrderPayload> createOrder(OrderPayload apiRequest) {

        logger.info("Validating request for order no: {}", apiRequest.getOrderNo());
        ResponseEntity<String> valError = orderValidator.validateOrderPayload(apiRequest);

        OrdersT orderDetails = OrderDetailsMapper.INSTANCE.toOrderDetailsBo(apiRequest);

        Boolean isValidUser = userValidation.validateUser(orderDetails.getUserId());
        logger.info("User {} is valid : {}", orderDetails.getUserId(), isValidUser);

        if (Boolean.TRUE.equals(isValidUser)) {
            if (Objects.isNull(valError)) {

                orderPersistence.saveOrder(orderDetails);

                //logger.debug("Order Details BO created for the order no: {}", apiRequest.getOrderNo());

                //kafkaMessagePublisher.sendOrderDetails(orderDetails);

            } else {
                logger.error("There is some unhandled error in Validation for order {}" +
                            "Please reach out to support !", apiRequest.getOrderNo());
                throw new RuntimeException("There is some unhandled error in Validation");
            }

            return new ApiResponse<>(HttpStatus.OK, null, "Order placed successfully");
        } else {
            logger.error("No user with User ID {} found !", orderDetails.getUserId());
            throw new UserNotFoundException("User with ID " + orderDetails.getUserId() + " not found!");
        }
    }

}