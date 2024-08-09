package com.foodDelivery.orderService.external.bo;

import java.time.LocalDateTime;
import java.util.List;

import com.foodDelivery.orderService.external.request.Address;
import com.foodDelivery.orderService.external.request.OrderStatus;
import com.foodDelivery.orderService.external.request.PaymentMethod;
import com.foodDelivery.orderService.external.request.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class OrderDetailsBo {

    private UserDetails user;
    private List<String> itemIds;
    private String orderNo;
    private OrderStatus status;
    private Double totalPrice;
    private Address deliveryAddress;
    private String restaurantName;
    private LocalDateTime orderPlaceTime;
    private PaymentMethod paymentMethod;
    private LocalDateTime deliveryTimeETA;
    private String contactNumber;
    private String specialInstructions;

}
