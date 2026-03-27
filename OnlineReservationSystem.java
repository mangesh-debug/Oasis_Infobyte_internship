import java.util.*;

class Reservation {
    int id;
    String name;
    String from;
    String to;
    int seats;

    Reservation(int id, String name, String from, String to, int seats) {
        this.id = id;
        this.name = name;
        this.from = from;
        this.to = to;
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + id + " | Name: " + name + " | Route: " + from + " -> " + to + " | Seats: " + seats;
    }
}

public class OnlineReservationSystem {
    static final int TOTAL_SEATS = 50;
    static int availableSeats = TOTAL_SEATS;

    static Map<Integer, Reservation> reservations = new HashMap<>();
    static int nextId = 1001;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n==== Online Reservation System ====");
            System.out.println("Available seats: " + availableSeats + "/" + TOTAL_SEATS);
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. View All Reservations");
            System.out.println("4. Exit");
            System.out.print("Choose: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    book(sc);
                    break;
                case "2":
                    cancel(sc);
                    break;
                case "3":
                    viewAll();
                    break;
                case "4":
                    System.out.println("Exiting... 👋");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static void book(Scanner sc) {
        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();

        System.out.print("From: ");
        String from = sc.nextLine().trim();

        System.out.print("To: ");
        String to = sc.nextLine().trim();

        System.out.print("Seats to book: ");
        int seats;
        try {
            seats = Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Invalid seats input.");
            return;
        }

        if (seats <= 0) {
            System.out.println("Seats must be > 0.");
            return;
        }
        if (seats > availableSeats) {
            System.out.println("Not enough seats available.");
            return;
        }

        int id = nextId++;
        Reservation r = new Reservation(id, name, from, to, seats);
        reservations.put(id, r);
        availableSeats -= seats;

        System.out.println("✅ Booking successful!");
        System.out.println(r);
    }

    static void cancel(Scanner sc) {
        System.out.print("Enter Reservation ID to cancel: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Invalid ID input.");
            return;
        }

        Reservation r = reservations.remove(id);
        if (r == null) {
            System.out.println("Reservation not found.");
            return;
        }
        availableSeats += r.seats;
        System.out.println("✅ Cancelled successfully: " + r);
    }

    static void viewAll() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations yet.");
            return;
        }
        System.out.println("\n--- All Reservations ---");
        for (Reservation r : reservations.values()) {
            System.out.println(r);
        }
    }
}