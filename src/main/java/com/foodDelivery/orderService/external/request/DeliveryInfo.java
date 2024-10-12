package com.foodDelivery.orderService.external.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeliveryInfo {

    private Address deliveryAddress;
    private String orderPlaceTime;
    private String deliveryTimeETA;

}
