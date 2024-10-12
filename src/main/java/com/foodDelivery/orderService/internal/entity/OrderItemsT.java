package com.foodDelivery.orderService.internal.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "ORDER_ITEMS_T")
public class OrderItemsT {

    @Id
    @Column(name = "item_id", nullable = false)
    private String itemId; // Primary key

    @Column(name = "order_id", nullable = false)
    private String orderId; // Foreign key to reference order

    @Column(name = "order_status", nullable = false)
    private String orderStatus; // Status of the order item

    @Column(name = "restaurant_name", nullable = false)
    private String restaurantName; // Name of the restaurant

    @Column(name = "restaurant_id", nullable = false)
    private int restaurantId; // Foreign key to reference restaurant

    @Column(nullable = false)
    private int quantity; // Quantity of the item ordered

    @Column(name = "price", precision = 10, scale = 2) // Precision and scale for DECIMAL
    private BigDecimal price;

    @Column(name = "insert_date", nullable = false)
    private Timestamp insertDate; // Date when the order item was inserted

    @Column(name = "upd_date", nullable = false)
    private Timestamp updDate; // Date when the order item was last updated

    // Getters and Setters
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Timestamp getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Timestamp insertDate) {
        this.insertDate = insertDate;
    }

    public Timestamp getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Timestamp updDate) {
        this.updDate = updDate;
    }
}