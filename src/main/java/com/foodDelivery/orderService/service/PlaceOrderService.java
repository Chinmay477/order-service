package com.foodDelivery.orderService.service;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodDelivery.orderService.exception.UserNotFoundException;
import com.foodDelivery.orderService.external.bo.OrderDetailsBo;
import com.foodDelivery.orderService.external.generic.ApiResponse;
import com.foodDelivery.orderService.external.request.OrderPayload;
import com.foodDelivery.orderService.internal.adapters.OrderDetailsMapper;
import com.foodDelivery.orderService.repository.UserRepository;
import com.foodDelivery.orderService.service.validation.OrderValidator;

@Service
public class PlaceOrderService {

    @Autowired
    OrderValidator orderValidator;

    @Autowired
    KafkaMessagePublisher kafkaMessagePublisher;

    @Autowired
    UserRepository userRepository;


    private static final Logger logger = LoggerFactory.getLogger(PlaceOrderService.class);

    public ApiResponse<OrderPayload> placeOrder(OrderPayload apiRequest) {

        logger.info("Validating reqeust for order no: {}", apiRequest.getOrderNo());
        ResponseEntity<String> valError = orderValidator.validateOrderPayload(apiRequest);

        Boolean isValidUser = userRepository.existsById(Integer.valueOf(apiRequest.getUserId()));
        
        if(isValidUser){
            if (Objects.isNull(valError)) {

                OrderDetailsBo orderDetails = OrderDetailsMapper.INSTANCE.toOrderDetailsBo(apiRequest);
                logger.debug("Order Details BO created for the order no: {}", apiRequest.getOrderNo());

                kafkaMessagePublisher.sendOrderDetails(orderDetails);

            } else {
                logger.error("There is some unhandled error in Validation for order {}" +
                        "Please reach out to support !", apiRequest.getOrderNo());
                throw new RuntimeException("There is some unhandled error in Validation");
            }

            // Just checking Jenkins Webhook again
            return new ApiResponse<>(HttpStatus.OK, null, "Order placed successfully");
        }
        else
            throw new UserNotFoundException("User with ID " + apiRequest.getUserId() + " not found!");
    }  
     
}
