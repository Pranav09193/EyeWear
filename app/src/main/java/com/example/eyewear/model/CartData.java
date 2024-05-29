package com.example.eyewear.model;

public class CartData {
    String imageUrl,name,currentDate,currentTime;
            String price;

    public CartData() {
    }

    public CartData(String imageUrl, String name, String currentDate, String currentTime, String price) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.currentDate = currentDate;
        this.currentTime = currentTime;
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
