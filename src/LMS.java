import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LMS {

    // holds every Patron currently in the system
    private ArrayList<Patron> patrons;

    // Constructor
    public LMS() {
        patrons = new ArrayList<Patron>();
    }

    // Adds a new patron to the system, as long as their ID isn't already used
    public boolean addPatron(Patron newPatron) {

        // Loop through every existing patron to check for a duplicate ID
        for (Patron p : patrons) {
            if (p.getPatronID().equals(newPatron.getPatronID())) {
                System.out.println("Error: A patron with ID " + newPatron.getPatronID() + " already exists.");
                return false; // stop here - duplicate found, don't add
            }
        }

        // No duplicate found - safe to add to the list
        patrons.add(newPatron);
        return true;
    }

    // Removes a patron from the system using their ID
    public boolean removePatron(String patronID) {

        // Loop through looking for a match
        for (Patron p : patrons) {
            if (p.getPatronID().equals(patronID)) {
                patrons.remove(p);
                return true;
            }
        }

        // If we get here, no match was found in the whole list
        System.out.println("Error: No patron found with ID " + patronID);
        return false;
    }

    // Searches for a patron by ID and returns that Patron object
    public Patron searchByID(String patronID) {

        // Loop through looking for a match
        for (Patron p : patrons) {
            if (p.getPatronID().equals(patronID)) {
                return p;
            }
        }

        // No match found anywhere in the list
        return null;
    }

    // Gives access to the full list of patrons (used by Main and for testing)
    public ArrayList<Patron> getAllPatrons() {
        return patrons;
    }

    // Prints every patron currently in the system
    public void displayAllPatrons() {

        // Handle the empty list case first
        if (patrons.isEmpty()) {
            System.out.println("No patrons currently in the system.");
            return;
        }

        // Print each patron using their toString() method
        for (Patron p : patrons) {
            System.out.println(p);
        }
    }

    // Reads a text file and loads each valid line as a new Patron
    public void loadFromFile(String filePath) {

        int addedCount = 0;

        try {
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("-", 4);

                if (parts.length != 4) {
                    System.out.println("Skipped invalid line (wrong format): " + line);
                    continue;
                }

                String id = parts[0];
                String name = parts[1];
                String address = parts[2];
                String fineString = parts[3];

                if (!id.matches("\\d{7}")) {
                    System.out.println("Skipped invalid line (bad ID): " + line);
                    continue;
                }

                double fine;
                try {
                    fine = Double.parseDouble(fineString);
                } catch (NumberFormatException e) {
                    System.out.println("Skipped invalid line (bad fine amount): " + line);
                    continue;
                }

                if (fine < 0 || fine > 250) {
                    System.out.println("Skipped invalid line (fine out of range): " + line);
                    continue;
                }

                Patron newPatron = new Patron(id, name, address, fine);
                boolean wasAdded = addPatron(newPatron);

                if (wasAdded) {
                    addedCount++;
                    System.out.println("Added patron: " + newPatron);
                }
            }

            fileScanner.close();
            System.out.println(addedCount + " patron(s) successfully added from file.");

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found at " + filePath);
        }
    }
}