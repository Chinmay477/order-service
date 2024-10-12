package com.foodDelivery.orderService.service.validation;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodDelivery.orderService.internal.entity.User;
import com.foodDelivery.orderService.service.internal.UserService;

@Service
public class ValidUserValidation {

    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(ValidUserValidation.class);

    public Boolean validateUser(Integer inUser){
        
        logger.debug("Fetching for User ID {}",inUser);
        User fetchedUser = userService.getUserById(inUser);

        if (Objects.nonNull(fetchedUser)) {
            return true;
        }

        logger.error("No user found for User ID {}",inUser);
        return false;
    }

}