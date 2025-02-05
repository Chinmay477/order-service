package com.foodDelivery.orderService.service;

import com.foodDelivery.orderService.exception.UserNotFoundException;
import com.foodDelivery.orderService.external.generic.ApiResponse;
import com.foodDelivery.orderService.external.request.OrderItem;
import com.foodDelivery.orderService.external.request.OrderPayload;
import com.foodDelivery.orderService.internal.adapters.OrderDetailsMapper;
import com.foodDelivery.orderService.internal.adapters.OrderItemsMapper;
import com.foodDelivery.orderService.internal.entity.OrderItemsT;
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

import java.util.ArrayList;
import java.util.List;

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

    /**
     *
     * @param apiRequest
     * @return
     */
    public ApiResponse<OrderPayload> createOrder(OrderPayload apiRequest) {

        String orderNo = apiRequest.getOrderNo();
        Integer userId = Integer.valueOf(apiRequest.getUserId());

        logger.info("Processing order creation request for order no: {}", orderNo);

        logger.info("Validating request for order no: {}", apiRequest.getOrderNo());
        validateOrderPayload(apiRequest);

        logger.info("Validating user {}", userId);
        validateUser(userId);

        OrdersT orderDetails = OrderDetailsMapper.INSTANCE.toOrderDetailsBo(apiRequest);
        List<OrderItemsT> orderItemsDetail = buildOrderItems(apiRequest);

        try {
            saveOrder(orderDetails, orderItemsDetail);
            publishOrderEvent(orderDetails);

            logger.info("Successfully created order: {}", orderNo);
            return new ApiResponse<>(
                    HttpStatus.OK,
                    apiRequest,
                    "Order placed successfully"
            );
        } catch (Exception e) {
            logger.error("Failed to create order: {}", orderNo, e);
            throw new RuntimeException("Failed to create order: " + orderNo, e);
        }

    }

    /**
     *
     * @param apiRequest
     */
    private void validateOrderPayload(OrderPayload apiRequest) {
        ResponseEntity<String> valError = orderValidator.validateOrderPayload(apiRequest);
        if (valError!=null && valError.getStatusCode() == HttpStatus.BAD_REQUEST) {
            logger.error("Validation failed for order: {}", apiRequest.getOrderNo());
            throw new RuntimeException("Order validation failed");
        }
    }

    /**
     *
     * @param userId
     */
    private void validateUser(Integer userId) {
        Boolean isValidUser = userValidation.validateUser(userId);
        logger.info("User {} is valid: {}", userId, isValidUser);
        if (!Boolean.TRUE.equals(isValidUser)) {
            logger.error("No user with User ID {} found!", userId);
            throw new UserNotFoundException("User with ID " + userId + " not found!");
        }
    }

    /**
     *
     * @param apiRequest
     * @return
     */
    private List<OrderItemsT> buildOrderItems(OrderPayload apiRequest) {
        List<OrderItemsT> orderItemsDetail = new ArrayList<>();
        for (OrderItem orderItem : apiRequest.getItems()) {
            try{
                OrderItemsT lineItem = OrderItemsMapper.INSTANCE.toOrderLinesBo(
                        orderItem,
                        apiRequest.getOrderNo(),
                        String.valueOf(apiRequest.getStatus())
                );
                orderItemsDetail.add(lineItem);
            }catch (Exception e){
                logger.error("Failed to map order item {} for order {}",
                        orderItem.getItemId(), apiRequest.getOrderNo(), e);
                throw new RuntimeException("Failed to process order item", e);
            }
        }
        return orderItemsDetail;
    }

    /**
     *
     * @param orderDetails
     * @param orderItemsDetail
     */
    private void saveOrder(OrdersT orderDetails, List<OrderItemsT> orderItemsDetail) {
        try {
            orderPersistence.saveOrder(orderDetails);
            orderPersistence.saveOrderItems(orderItemsDetail);
            logger.info("Successfully persisted order {} with {} items",
                    orderDetails.getOrderId(), orderItemsDetail.size());
        } catch (Exception e) {
            logger.error("Failed to persist order {}", orderDetails.getOrderId(), e);
            throw new RuntimeException("Failed to save order", e);
        }
    }

    /**
     *
     * @param orderDetails
     */
    private void publishOrderEvent(OrdersT orderDetails) {
        try {
            //kafkaMessagePublisher.sendOrderDetails(orderDetails);
            logger.info("Successfully published order event for order {}",
                    orderDetails.getOrderId());
        } catch (Exception e) {
            logger.error("Failed to publish order event for order {}",
                    orderDetails.getOrderId(), e);
            throw new RuntimeException("Failed to publish order event", e);
        }
    }
}