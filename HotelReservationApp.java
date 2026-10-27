package com.core;
import java.util.ArrayList;
import java.util.Scanner;

public class HotelReservationApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>();
        HotelService service = new HotelServiceImplementation(sc);

        while (true) {
            System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");
            System.out.println("1. User Registration");
            System.out.println("2. User Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    registerUser(sc, users);
                    break;

                case "2":
                    User loggedInUser = loginUser(sc, users);

                    if (loggedInUser != null) {
                        userMenu(sc, service, loggedInUser);
                    }
                    break;

                case "3":
                    adminLogin(sc, service);
                    break;

                case "4":
                    System.out.println("Thank you for using our system!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void registerUser(
            Scanner sc, ArrayList<User> users) {

        System.out.println("\n--- USER REGISTRATION ---");

        System.out.print("Create username: ");
        String username = sc.nextLine().trim();

        if (username.isEmpty()) {
            System.out.println("Username cannot be empty.");
            return;
        }

        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                System.out.println("Username already exists.");
                return;
            }
        }

        System.out.print("Create password: ");
        String password = sc.nextLine();

        if (password.length() < 4) {
            System.out.println(
                "Password must contain at least 4 characters."
            );
            return;
        }

        users.add(new User(username, password));

        System.out.println("Registration successful!");
    }

    private static User loginUser(
            Scanner sc, ArrayList<User> users) {

        System.out.println("\n--- USER LOGIN ---");

        System.out.print("Enter username: ");
        String username = sc.nextLine().trim();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)
                    && user.checkPassword(password)) {

                System.out.println("Login successful!");
                System.out.println(
                    "Welcome, " + user.getUsername()
                );

                return user;
            }
        }

        System.out.println("Invalid username or password.");
        return null;
    }

    private static void userMenu(
            Scanner sc, HotelService service, User user) {

        while (true) {
            System.out.println(
                "\n===== USER MENU: "
                + user.getUsername() + " ====="
            );

            System.out.println("1. View Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. View Reservations");
            System.out.println("4. Search Reservation");
            System.out.println("5. Cancel Reservation");
            System.out.println("6. Logout");
            System.out.print("Enter choice: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    service.viewRooms();
                    break;

                case "2":
                    service.bookRoom();
                    break;

                case "3":
                    service.viewReservations();
                    break;

                case "4":
                    service.searchReservation();
                    break;

                case "5":
                    service.cancelReservation();
                    break;

                case "6":
                    System.out.println("Logged out successfully.");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void adminLogin(
            Scanner sc, HotelService service) {

        System.out.println("\n--- ADMIN LOGIN ---");

        System.out.print("Enter admin username: ");
        String username = sc.nextLine();

        System.out.print("Enter admin password: ");
        String password = sc.nextLine();

        if (username.equals("admin")
                && password.equals("admin123")) {

            System.out.println("Admin login successful!");
            service.adminViewReservations();

        } else {
            System.out.println("Invalid admin credentials.");
        }
    }
}