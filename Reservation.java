package com.core;



public class Reservation {
    private int reservationId;
    private String customerName;
    private String contactNumber;
    private Room room;

    public Reservation(int reservationId, String customerName,
                       String contactNumber, Room room) {
        this.reservationId = reservationId;
        this.customerName = customerName;
        this.contactNumber = contactNumber;
        this.room = room;
    }

    public int getReservationId() {
        return reservationId;
    }

    public Room getRoom() {
        return room;
    }

    public void displayReservation() {
        System.out.println("-------------------------");
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Contact Number: " + contactNumber);
        System.out.println("Room ID: " + room.getRoomId());
        System.out.println("Category: " + room.getCategory());
        System.out.println("Price: Rs." + room.getPrice());
        System.out.println("-------------------------");
    }
}
