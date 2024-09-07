package com.foodDelivery.orderService.external.request;

public enum OrderStatus {

    NEW_ORDER,
    ORDER_PROCESSING,
    PAYMENT_PENDING,
    PAYMENT_PROCESSING,
    PAYMENT_SUCCESS,
    PAYMENT_FAILURE,
    ORDER_CONFIRMED,
    ORDER_PREPARING,
    ORDER_IN_TRANSIT,
    ORDER_DELIVERED,
    ORDER_CANCELLED;

    public static boolean isValid(String value) {
        for (OrderStatus status : values()) {
            if (status.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
    
}
