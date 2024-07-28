package com.foodDelivery.orderService.external.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Address {

    private String address1;
    private String city;
    private String state;
    private String zipCode;
    
}
