package com.foodDelivery.orderService.external.request;

import java.util.List;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class OrderPayload {

    private String useCaseName;
    private String userId;
    
    @NotBlank(message = "Order number must not be blank")
    @Size(min = 6, message = "Order number must be at least 6 characters long")
    private String orderNo;

    private List<String> itemIds;
    private OrderStatus status;

    @DecimalMin(value = "0.01", inclusive = true, message = "Total price must be greater than 0")
    private String totalPrice;

    private Address deliveryAddress;
    private String restaurantName;
    private String orderPlaceTime;
    private PaymentMethod paymentMethod;

    private String deliveryTimeETA;
    
    @Size(min = 10, max = 10, message = "Contact number must be exactly 10 characters long")
    private String contactNumber;
    private String specialInstructions;

}
