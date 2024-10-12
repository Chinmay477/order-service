package com.foodDelivery.orderService.internal.adapters;

import com.foodDelivery.orderService.external.request.Address;
import com.foodDelivery.orderService.external.request.OrderPayload;
import com.foodDelivery.orderService.internal.entity.OrdersT;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper
public interface OrderDetailsMapper {

    OrderDetailsMapper INSTANCE = Mappers.getMapper(OrderDetailsMapper.class);

    @Mappings({
            @Mapping(source="orderNo",target="orderId"),
            @Mapping(source = "userId", target = "userId"),
            @Mapping(source = "status", target = "orderStatus"),
            @Mapping(source = "restaurantName", target = "restaurantName"),
            @Mapping(source = "restaurantId", target = "restaurantId"),
            @Mapping(source = "totalPrice", target = "totalPrice", numberFormat = "#.##"),
            @Mapping(target = "insertDate", expression = "java(java.time.LocalDateTime.now())"),
            @Mapping(source = "paymentMethod", target = "paymentMethod"),
            @Mapping(source = "deliveryInfo.deliveryAddress", target = "deliveryAddress", qualifiedByName = "combinedAddress"),
            @Mapping(source = "contactNumber", target = "contactNumber"),
            @Mapping(source = "specialInstructions", target = "specialInstructions")
    })
    OrdersT toOrderDetailsBo(OrderPayload orderPayload);

    @Named("combinedAddress")
    default String combinedAddress(Address address){
        return String.format("%s, %s, %s, %s",
                address.getAddress1(),
                address.getCity(),
                address.getState(),
                address.getZipCode()
                );
    }

}
