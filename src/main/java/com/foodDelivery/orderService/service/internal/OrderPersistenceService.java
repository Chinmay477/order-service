package com.foodDelivery.orderService.service.internal;

import com.foodDelivery.orderService.external.request.OrderItem;
import com.foodDelivery.orderService.internal.entity.OrderItemsT;
import com.foodDelivery.orderService.internal.entity.OrdersT;
import com.foodDelivery.orderService.repository.OrderItemsRepository;
import com.foodDelivery.orderService.repository.OrdersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderPersistenceService {

    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    OrderItemsRepository orderItemsRepository;

    @Transactional
    public void saveOrder(OrdersT order) {
        ordersRepository.save(order);
    }

    @Transactional
    public void saveOrderItems(List<OrderItemsT> orderItems){
        for(OrderItemsT orderItem : orderItems){
            orderItemsRepository.save(orderItem);
        }
    }

}
