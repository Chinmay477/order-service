package com.foodDelivery.orderService.repository;

import com.foodDelivery.orderService.internal.entity.OrdersT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersT,String> {

}
