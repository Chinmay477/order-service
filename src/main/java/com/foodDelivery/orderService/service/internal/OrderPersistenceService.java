package com.foodDelivery.orderService.service.internal;

import com.foodDelivery.orderService.internal.entity.OrdersT;
import com.foodDelivery.orderService.repository.OrdersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderPersistenceService {

    @Autowired
    OrdersRepository ordersRepository;

    @Transactional
    public OrdersT saveOrder(OrdersT order) {
        return ordersRepository.save(order);
    }

}
