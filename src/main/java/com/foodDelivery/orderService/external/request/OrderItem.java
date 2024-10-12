package com.foodDelivery.orderService.external.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItem {

    @NotBlank(message = "Item ID must not be blank")
    private String itemId;

    @NotBlank(message = "Restaurant Name must not be blank")
    private String restaurantName;

    @NotBlank(message = "Restaurant Id must not be blank")
    private String restaurantId;

    @DecimalMin(value = "1", message = "Quantity must be at least 1")
    private int quantity;

    @DecimalMin(value = "0.01", message = "Total price must be greater than 0")
    private double price;

}
