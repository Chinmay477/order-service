package com.foodDelivery.orderService.external.request;

public enum OrderStatus {

    PAYMENT_PENDING,
    PAYMENT_PROCESSING,
    PAYMENT_COMPLETE,
    PLACED,
    IN_PREPARATION,
    IN_TRANSIT,
    DELIVERED,
    CANCELLED;

    public static boolean isValid(String value) {
        for (OrderStatus status : values()) {
            if (status.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
    
}
