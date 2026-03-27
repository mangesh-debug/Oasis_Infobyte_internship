import java.util.*;

class Book {
    int id;
    String title;
    String author;
    boolean issued;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = false;
    }

    @Override
    public String toString() {
        return "Book ID: " + id + " | " + title + " by " + author + " | " + (issued ? "Issued ❌" : "Available ✅");
    }
}

public class DigitalLibraryManagement {
    static Map<Integer, Book> books = new HashMap<>();
    static int nextId = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n==== Digital Library Management ====");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book (by title)");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Choose: ");

            String ch = sc.nextLine().trim();
            switch (ch) {
                case "1": addBook(sc); break;
                case "2": viewAll(); break;
                case "3": search(sc); break;
                case "4": issue(sc); break;
                case "5": ret(sc); break;
                case "6":
                    System.out.println("Exiting... 👋");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static void addBook(Scanner sc) {
        System.out.print("Enter title: ");
        String title = sc.nextLine().trim();
        System.out.print("Enter author: ");
        String author = sc.nextLine().trim();

        Book b = new Book(nextId++, title, author);
        books.put(b.id, b);

        System.out.println("✅ Book added: " + b);
    }

    static void viewAll() {
        if (books.isEmpty()) {
            System.out.println("No books added yet.");
            return;
        }
        System.out.println("\n--- All Books ---");
        for (Book b : books.values()) System.out.println(b);
    }

    static void search(Scanner sc) {
        System.out.print("Enter title keyword: ");
        String key = sc.nextLine().trim().toLowerCase();

        boolean found = false;
        for (Book b : books.values()) {
            if (b.title.toLowerCase().contains(key)) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) System.out.println("No matching books found.");
    }

    static void issue(Scanner sc) {
        System.out.print("Enter Book ID to issue: ");
        int id = readInt(sc);
        Book b = books.get(id);

        if (b == null) {
            System.out.println("Book not found.");
            return;
        }
        if (b.issued) {
            System.out.println("Book is already issued.");
            return;
        }
        b.issued = true;
        System.out.println("✅ Book issued: " + b);
    }

    static void ret(Scanner sc) {
        System.out.print("Enter Book ID to return: ");
        int id = readInt(sc);
        Book b = books.get(id);

        if (b == null) {
            System.out.println("Book not found.");
            return;
        }
        if (!b.issued) {
            System.out.println("Book is already available.");
            return;
        }
        b.issued = false;
        System.out.println("✅ Book returned: " + b);
    }

    static int readInt(Scanner sc) {
        while (true) {
            String s = sc.nextLine().trim();
            try { return Integer.parseInt(s); }
            catch (Exception e) { System.out.print("Enter a valid number: "); }
        }
    }
}