package com.core;



import java.util.ArrayList;
import java.util.Scanner;

public class HotelServiceImplementation implements HotelService {

    private Scanner sc;
    private ArrayList<Room> rooms;
    private ArrayList<Reservation> reservations;
    private int nextReservationId = 1001;

    public HotelServiceImplementation(Scanner sc) {
        this.sc = sc;
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();

        rooms.add(new Room(101, "Single", 1000));
        rooms.add(new Room(102, "Single", 1000));
        rooms.add(new Room(201, "Double", 2000));
        rooms.add(new Room(202, "Double", 2000));
        rooms.add(new Room(301, "Deluxe", 3500));
    }

    @Override
    public void viewRooms() {
        System.out.println("\n--- HOTEL ROOMS ---");

        for (Room room : rooms) {
            room.displayRoom();
        }
    }

    @Override
    public void bookRoom() {
        try {
            viewRooms();

            System.out.print("Enter room ID: ");
            int roomId = Integer.parseInt(sc.nextLine());

            Room selectedRoom = null;

            for (Room room : rooms) {
                if (room.getRoomId() == roomId) {
                    selectedRoom = room;
                    break;
                }
            }

            if (selectedRoom == null) {
                System.out.println("Room not found.");
                return;
            }

            if (!selectedRoom.isAvailable()) {
                System.out.println("Room is already booked.");
                return;
            }

            System.out.print("Enter customer name: ");
            String name = sc.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Name cannot be empty.");
                return;
            }

            System.out.print("Enter contact number: ");
            String contact = sc.nextLine().trim();

            if (!contact.matches("[0-9]{10}")) {
                System.out.println(
                    "Contact number must contain 10 digits."
                );
                return;
            }

            Reservation reservation = new Reservation(
                nextReservationId++, name, contact, selectedRoom
            );

            reservations.add(reservation);
            selectedRoom.setAvailable(false);

            System.out.println("Room booked successfully!");
            System.out.println(
                "Reservation ID: "
                + reservation.getReservationId()
            );

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid numeric ID.");
        }
    }

    @Override
    public void viewReservations() {
        System.out.println("\n--- MY RESERVATIONS ---");

        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }

        for (Reservation reservation : reservations) {
            reservation.displayReservation();
        }
    }

    @Override
    public void searchReservation() {
        try {
            System.out.print("Enter reservation ID: ");
            int id = Integer.parseInt(sc.nextLine());

            for (Reservation reservation : reservations) {
                if (reservation.getReservationId() == id) {
                    reservation.displayReservation();
                    return;
                }
            }

            System.out.println("Reservation not found.");

        } catch (NumberFormatException e) {
            System.out.println("Invalid reservation ID.");
        }
    }

    @Override
    public void cancelReservation() {
        try {
            System.out.print("Enter reservation ID to cancel: ");
            int id = Integer.parseInt(sc.nextLine());

            for (Reservation reservation : reservations) {
                if (reservation.getReservationId() == id) {
                    reservation.getRoom().setAvailable(true);
                    reservations.remove(reservation);

                    System.out.println(
                        "Reservation cancelled successfully."
                    );
                    return;
                }
            }

            System.out.println("Reservation not found.");

        } catch (NumberFormatException e) {
            System.out.println("Invalid reservation ID.");
        }
    }

    @Override
    public void adminViewReservations() {
        System.out.println("\n--- ADMIN: ALL RESERVATIONS ---");

        if (reservations.isEmpty()) {
            System.out.println("No reservations available.");
            return;
        }

        for (Reservation reservation : reservations) {
            reservation.displayReservation();
        }
    }
}