package com.foodDelivery.orderService.repository;

import com.foodDelivery.orderService.internal.entity.OrderItemsT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItemsT,String> {

}
