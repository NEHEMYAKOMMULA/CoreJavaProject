package com.core;


public class Room {
    private int roomId;
    private String category;
    private double price;
    private boolean available;

    public Room(int roomId, String category, double price) {
        this.roomId = roomId;
        this.category = category;
        this.price = price;
        this.available = true;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void displayRoom() {
        System.out.println(
            "Room ID: " + roomId
            + " | Category: " + category
            + " | Price: Rs." + price
            + " | Status: "
            + (available ? "Available" : "Booked")
        );
    }
}
