package com.example.demo.DTO;

public class BookBedDTO {
    public String userId;
    public Integer quantity;

    public BookBedDTO(String userId, Integer quantity) {
        this.userId = userId;
        this.quantity = quantity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
