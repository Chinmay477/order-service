package com.foodDelivery.orderService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodDelivery.orderService.internal.entity.OrderItems;


@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Object> {
    
}
