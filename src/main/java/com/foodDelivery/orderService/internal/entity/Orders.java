package com.foodDelivery.orderService.internal.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "ORDERS")
public class Orders {

    @Id
    @Column(name = "ORDER_NO")
    private String orderNo;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;

    @Column(name = "RESTAURANT_NAME")
    private String restaurantName;

    @Column(name = "ORDER_PLACE_TIME")
    private LocalDateTime orderPlaceTime;

    @Column(name = "PAYMENT_METHOD")
    private String paymentMethod;

    @Column(name = "DELIVERY_TIME_ETA")
    private LocalDateTime deliveryTimeEta;

    @Column(name = "CONTACT_NUMBER")
    private String contactNumber;

    @Column(name = "SPECIAL_INSTRUCTIONS")
    private String specialInstructions;

    @OneToMany(mappedBy = "order")
    private Set<OrderItems> orderItems;

    @OneToOne(mappedBy = "order")
    private DeliveryAddress deliveryAddress;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public LocalDateTime getOrderPlaceTime() {
        return orderPlaceTime;
    }

    public void setOrderPlaceTime(LocalDateTime orderPlaceTime) {
        this.orderPlaceTime = orderPlaceTime;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getDeliveryTimeEta() {
        return deliveryTimeEta;
    }

    public void setDeliveryTimeEta(LocalDateTime deliveryTimeEta) {
        this.deliveryTimeEta = deliveryTimeEta;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public Set<OrderItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    
}
