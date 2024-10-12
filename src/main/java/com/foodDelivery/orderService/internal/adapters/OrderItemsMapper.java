package com.foodDelivery.orderService.internal.adapters;

import com.foodDelivery.orderService.external.request.OrderItem;
import com.foodDelivery.orderService.external.request.OrderPayload;
import com.foodDelivery.orderService.internal.entity.OrderItemsT;
import com.foodDelivery.orderService.internal.entity.OrdersT;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderItemsMapper {

    OrderItemsMapper INSTANCE = Mappers.getMapper(OrderItemsMapper.class);

    @Mappings({
            @Mapping(source = "orderItem.itemId",target="itemId"),
            @Mapping(source = "orderNo",target="orderId"),
            @Mapping(source = "status",target="orderStatus"),
            @Mapping(source = "orderItem.restaurantName", target = "restaurantName"),
            @Mapping(source = "orderItem.restaurantId", target = "restaurantId"),
            @Mapping(source = "orderItem.quantity", target = "quantity"),
            @Mapping(source = "orderItem.price", target = "price", numberFormat = "#.##"),
            @Mapping(target = "insertDate", expression = "java(java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()))")
    })
    OrderItemsT toOrderLinesBo(OrderItem orderItem, String orderNo, String status);

}
