package com.foodDelivery.orderService.service.validation;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodDelivery.orderService.external.request.UserDetails;
import com.foodDelivery.orderService.internal.entity.User;
import com.foodDelivery.orderService.service.internal.UserService;

@Service
public class ValidUserValidation {

    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(ValidUserValidation.class);

    public Boolean validateUser(UserDetails inUser){
        
        logger.debug("Fetching for User ID {}",inUser.getUserID());
        User fetchedUser = userService.getUserById(Integer.valueOf(inUser.getUserID()));

        if (Objects.isNull(fetchedUser)) {
            logger.warn("No user found for User ID {}",inUser.getUserID());
            return false;
        }

        boolean isValid = fetchedUser.getUsername().equals(inUser.getUsername()) 
        && fetchedUser.getFirstName().equals(inUser.getFirstName())
        && fetchedUser.getLastName().equals(inUser.getLastName());
        logger.info("User validation for User ID {} is {}",inUser.getUserID(), isValid);
    
        return isValid;
    }

}