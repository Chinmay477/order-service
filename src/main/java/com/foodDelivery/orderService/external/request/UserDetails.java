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
public class UserDetails {
    private String userID;
    private String username;
    private String firstName;
    private String lastName;
}
