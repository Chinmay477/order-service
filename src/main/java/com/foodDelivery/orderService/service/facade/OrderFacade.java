package com.foodDelivery.orderService.service.facade;

import com.foodDelivery.orderService.external.generic.ApiResponse;
import com.foodDelivery.orderService.external.request.OrderPayload;
import com.foodDelivery.orderService.internal.entity.OrderItemsT;
import com.foodDelivery.orderService.internal.entity.OrdersT;
import com.foodDelivery.orderService.service.CreateOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderFacade {

    @Autowired
    CreateOrderService createOrderService;

    private static final Logger logger = LoggerFactory.getLogger(OrderFacade.class);

    /**
     *
     * @param apiRequest
     * @return ApiResponse
     */
    public ApiResponse<OrderPayload> initiateOrder(OrderPayload apiRequest) {

        String orderNo = apiRequest.getOrderNo();
        Integer userId = Integer.valueOf(apiRequest.getUserId());

        logger.info("Processing order creation request for order no: {}", orderNo);

        logger.info("Validating request for order no: {}", orderNo);
        createOrderService.validateOrderPayload(apiRequest);

        logger.info("Validating user {}", userId);
        createOrderService.validateUser(userId);

        logger.info("Creating order details and item details for order no: {}", orderNo);
        OrdersT orderDetails = createOrderService.createOrderDetails(apiRequest);
        List<OrderItemsT> orderItemsDetail = createOrderService.buildOrderItems(apiRequest);

        try {
            createOrderService.saveOrder(orderDetails, orderItemsDetail);
            createOrderService.publishOrderEvent(orderDetails);
            return new ApiResponse<>(HttpStatus.OK, apiRequest, "Order placed successfully");
        } catch (Exception e) {
            throw new RuntimeException("Failed to create order: " + orderNo, e);
        }
    }
}
