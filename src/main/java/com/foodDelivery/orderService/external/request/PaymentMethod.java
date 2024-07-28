package com.foodDelivery.orderService.external.request;

public enum PaymentMethod {

    CREDIT_CARD,
    DEBIT_CARD,
    UPI,
    CASH_ON_DELIVERY;

    public static boolean isValid(String value) {
        for (PaymentMethod method : values()) {
            if (method.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
    
}
