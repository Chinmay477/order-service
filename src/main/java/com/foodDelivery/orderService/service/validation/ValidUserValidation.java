package com.foodDelivery.orderService.service.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foodDelivery.orderService.exception.UserNotFoundException;
import com.foodDelivery.orderService.external.request.OrderPayload;
import com.foodDelivery.orderService.internal.entity.User;
import com.foodDelivery.orderService.repository.UserRepository;

@Component
public class ValidUserValidation {

    @Autowired
    UserRepository userRepository;


    public Boolean validateUser(OrderPayload request){


        Optional<User> fetchedUser = userRepository.findById(Integer.valueOf(request.getUser().getUserID()));


        if (fetchedUser.isEmpty()) {
            throw new UserNotFoundException("User with ID " + request.getUser().getUserID() + " not found!");
        }

        User user = fetchedUser.get();

        boolean isValid = user.getUsername().equals(request.getUser().getUsername()) 
        && user.getFirstName().equals(request.getUser().getFirstName())
        && user.getLastName().equals(request.getUser().getLastName());

        if (!isValid) {
            throw new UserNotFoundException("Incorrect Details for user with ID " + request.getUser().getUserID());
        }
    
        return isValid;
    }

}
