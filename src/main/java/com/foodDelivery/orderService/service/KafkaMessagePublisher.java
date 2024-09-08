package com.foodDelivery.orderService.service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.foodDelivery.orderService.constants.OrdersConstants;
import com.foodDelivery.orderService.external.bo.OrderDetailsBo;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private static final Logger logger = LoggerFactory.getLogger(KafkaMessagePublisher.class);

    public void sendOrderDetails(OrderDetailsBo orderDetails) {

            logger.info("Publishing order details for order no: {}", orderDetails.getOrderNo());

            CompletableFuture<SendResult<String, Object>> future = 
                kafkaTemplate.send(OrdersConstants.ORDER_DETAILS_TOPIC,orderDetails);

            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    logger.info("Published order details = [" + orderDetails + "] with offset=[" + 
                        result.getRecordMetadata().offset() + "]");
                } 
                else {
                    logger.error("Unable to publish order details =[" + orderDetails + "] due to : " + ex.getMessage());
                    throw new RuntimeException("Unable to publish Order Details for order: "+orderDetails.getOrderNo(),ex);
                }
            });

    }

}
