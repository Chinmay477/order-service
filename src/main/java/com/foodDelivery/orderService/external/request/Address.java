package com.foodDelivery.orderService.external.request;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "address1 must not be blank")
    private String address1;
    @NotBlank(message = "city must not be blank")
    private String city;
    @NotBlank(message = "state must not be blank")
    private String state;
    @NotBlank(message = "zipCode must not be blank")
    private String zipCode;
    
}
