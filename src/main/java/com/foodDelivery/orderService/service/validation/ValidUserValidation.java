package com.foodDelivery.orderService.service.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foodDelivery.orderService.external.request.UserDetails;
import com.foodDelivery.orderService.internal.entity.User;
import com.foodDelivery.orderService.repository.UserRepository;

@Component
public class ValidUserValidation {

    @Autowired
    UserRepository userRepository;


    public Boolean validateUser(UserDetails inUser){


        Optional<User> fetchedUser = userRepository.findById(Integer.valueOf(inUser.getUserID()));


        if (fetchedUser.isEmpty()) {
            return false;
        }

        User user = fetchedUser.get();

        boolean isValid = user.getUsername().equals(inUser.getUsername()) 
        && user.getFirstName().equals(inUser.getFirstName())
        && user.getLastName().equals(inUser.getLastName());
    
        return isValid;
    }

}
