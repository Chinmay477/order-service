package com.foodDelivery.orderService.internal.adapters;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.foodDelivery.orderService.external.bo.OrderDetailsBo;
import com.foodDelivery.orderService.external.request.OrderPayload;

@Mapper
public interface OrderDetailsMapper {

    OrderDetailsMapper INSTANCE = Mappers.getMapper(OrderDetailsMapper.class);

    @Mappings({
            @Mapping(source = "userId", target = "userId", numberFormat = "#"),
            @Mapping(source = "itemIds", target = "itemIds"),
            @Mapping(source="orderNo",target="orderNo"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "totalPrice", target = "totalPrice", numberFormat = "#.##"),
            @Mapping(source = "deliveryAddress", target = "deliveryAddress"),
            @Mapping(source = "restaurantName", target = "restaurantName"),
            @Mapping(source = "orderPlaceTime", target = "orderPlaceTime", dateFormat = "dd-MM-yyyy'T'HH:mm:ss"),
            @Mapping(source = "paymentMethod", target = "paymentMethod"),
            @Mapping(source = "deliveryTimeETA", target = "deliveryTimeETA", dateFormat = "dd-MM-yyyy'T'HH:mm:ss"),
            @Mapping(source = "contactNumber", target = "contactNumber"),
            @Mapping(source = "specialInstructions", target = "specialInstructions"),
    })
    OrderDetailsBo toOrderDetailsBo(OrderPayload orderPayload);

}
