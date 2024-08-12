package com.foodDelivery.orderService.external.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {

    private String address1;
    private String city;
    private String state;
    private String zipCode;
    
}
