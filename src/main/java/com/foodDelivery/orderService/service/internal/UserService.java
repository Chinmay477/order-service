package com.foodDelivery.orderService.service.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.foodDelivery.orderService.internal.entity.User;
import com.foodDelivery.orderService.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    
    @Cacheable(value = "users", key = "#userID")
    public User getUserById(Integer userID) {
        logger.info("Not cached, fetching by ID from Database");
        return userRepo.findById(userID).orElse(null);
    }
    
}
