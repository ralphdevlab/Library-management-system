import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        LMS lms = new LMS();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    handleLoadFile(lms, scanner);
                    break;
                case "2":
                    handleAddManual(lms, scanner);
                    break;
                case "3":
                    handleRemove(lms, scanner);
                    break;
                case "4":
                    handleSearch(lms, scanner);
                    break;
                case "5":
                    lms.displayAllPatrons();
                    break;
                case "6":
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    public static void displayMenu() {
        System.out.println("\n===== Library Management System =====");
        System.out.println("1. Load patrons from file");
        System.out.println("2. Add a patron manually");
        System.out.println("3. Remove a patron");
        System.out.println("4. Search for a patron");
        System.out.println("5. Display all patrons");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    public static void handleAddManual(LMS lms, Scanner scanner) {

        String id;
        while (true) {
            System.out.print("Enter patron ID (7 digits): ");
            id = scanner.nextLine();

            if (id.matches("\\d{7}")) {
                break;
            }
            System.out.println("Invalid ID. Must be exactly 7 digits.");
        }

        System.out.print("Enter patron name: ");
        String name = scanner.nextLine();

        System.out.print("Enter patron address: ");
        String address = scanner.nextLine();

        double fine = -1;
        while (true) {
            System.out.print("Enter overdue fine amount (0-250): ");
            String fineInput = scanner.nextLine();

            try {
                fine = Double.parseDouble(fineInput);
                if (fine >= 0 && fine <= 250) {
                    break;
                } else {
                    System.out.println("Fine must be between $0 and $250.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a numeric value.");
            }
        }

        Patron newPatron = new Patron(id, name, address, fine);
        boolean success = lms.addPatron(newPatron);

        if (success) {
            System.out.println("Patron added successfully!");
        }
    }

    public static void handleLoadFile(LMS lms, Scanner scanner) {
        System.out.print("Enter the path to the patron file: ");
        String filePath = scanner.nextLine().trim();

        lms.loadFromFile(filePath);
    }

    public static void handleRemove(LMS lms, Scanner scanner) {
        System.out.print("Enter the 7-digit ID of the patron to remove: ");
        String id = scanner.nextLine().trim();

        boolean success = lms.removePatron(id);

        if (success) {
            System.out.println("Patron removed successfully.");
        }
        // if it failed, removePatron() already printed the "not found" error
    }

    public static void handleSearch(LMS lms, Scanner scanner) {
        System.out.print("Enter the 7-digit ID to search for: ");
        String id = scanner.nextLine().trim();

        Patron found = lms.searchByID(id);

        if (found != null) {
            System.out.println("Patron found:");
            System.out.println(found);
        } else {
            System.out.println("No patron found with ID " + id);
        }
    }
}