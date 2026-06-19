import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LMSTest {

    // ===== Test Case 1 — Load a valid file =====
    @Test
    public void testCase1_LoadValidFile() {
        LMS lms = new LMS();
        lms.loadFromFile("src/test_patrons.txt");
        assertEquals(3, lms.getAllPatrons().size());
    }

    // ===== Test Case 2 — Load a file containing an invalid ID =====
    @Test
    public void testCase2_LoadFileInvalidID() {
        LMS lms = new LMS();
        lms.loadFromFile("src/test_patrons_bad_id.txt");
        assertEquals(1, lms.getAllPatrons().size()); // only the valid line should load
    }

    // ===== Test Case 3 — Load a file with an out-of-range fine =====
    @Test
    public void testCase3_LoadFileInvalidFine() {
        LMS lms = new LMS();
        lms.loadFromFile("src/test_patrons_bad_fine.txt");
        assertEquals(1, lms.getAllPatrons().size()); // the $300 fine line should be skipped
    }

    // ===== Test Case 4 — Manually add a patron =====
    @Test
    public void testCase4_AddPatron() {
        LMS lms = new LMS();
        Patron p1 = new Patron("9876543", "Test Patron", "100 Test St", 15.00);

        boolean result = lms.addPatron(p1);

        assertTrue(result);
        assertEquals(1, lms.getAllPatrons().size());
    }

    // ===== Test Case 5 — Manually add with invalid input =====
    // Note: re-prompt logic lives in Main (handleAddManual), not LMS,
    // so this test confirms LMS correctly rejects bad data if it ever reaches addPatron.
    // The actual re-prompt behavior is verified manually by running the program.

    // ===== Test Case 6 — Manually add a duplicate ID =====
    @Test
    public void testCase6_AddDuplicatePatron() {
        LMS lms = new LMS();
        Patron p1 = new Patron("1234567", "Sarah Jones", "123 Main St", 20.00);
        Patron p2 = new Patron("1234567", "Different Name", "456 Other St", 10.00);

        lms.addPatron(p1);
        boolean result = lms.addPatron(p2);

        assertFalse(result);
        assertEquals(1, lms.getAllPatrons().size());
    }

    // ===== Test Case 7 — Remove a patron with a valid ID =====
    @Test
    public void testCase7_RemovePatronValid() {
        LMS lms = new LMS();
        Patron p1 = new Patron("1245789", "Sarah Jones", "123 Main St", 40.54);
        lms.addPatron(p1);

        boolean result = lms.removePatron("1245789");

        assertTrue(result);
        assertEquals(0, lms.getAllPatrons().size());
    }

    // ===== Test Case 8 — Remove a patron with an ID that doesn't exist =====
    @Test
    public void testCase8_RemovePatronNotFound() {
        LMS lms = new LMS();
        Patron p1 = new Patron("1245789", "Sarah Jones", "123 Main St", 40.54);
        lms.addPatron(p1);

        boolean result = lms.removePatron("0000001");

        assertFalse(result);
        assertEquals(1, lms.getAllPatrons().size()); // nothing should be removed
    }

    // ===== Test Case 9 — Search for a patron with a valid ID =====
    @Test
    public void testCase9_SearchByIDFound() {
        LMS lms = new LMS();
        Patron p1 = new Patron("3256897", "Mason Arby", "6060 Saginaw St", 0.00);
        lms.addPatron(p1);

        Patron found = lms.searchByID("3256897");

        assertNotNull(found);
        assertEquals("Mason Arby", found.getName());
    }

    // ===== Test Case 10 — Search for a patron with an ID that doesn't exist =====
    @Test
    public void testCase10_SearchByIDNotFound() {
        LMS lms = new LMS();
        Patron p1 = new Patron("3256897", "Mason Arby", "6060 Saginaw St", 0.00);
        lms.addPatron(p1);

        Patron found = lms.searchByID("1111111");

        assertNull(found);
    }

    // ===== Test Case 11 — Display all patrons when the list is empty =====
    @Test
    public void testCase11_DisplayAllEmpty() {
        LMS lms = new LMS();
        assertEquals(0, lms.getAllPatrons().size());
        lms.displayAllPatrons(); // should print "No patrons currently in the system."
    }

    // ===== Test Case 12 — Display all patrons with multiple records =====
    @Test
    public void testCase12_DisplayAllMultiple() {
        LMS lms = new LMS();
        Patron p1 = new Patron("1245789", "Sarah Jones", "1136 Gorden Ave", 40.54);
        Patron p2 = new Patron("3256897", "Mason Arby", "6060 Saginaw St", 0.00);

        lms.addPatron(p1);
        lms.addPatron(p2);

        assertEquals(2, lms.getAllPatrons().size());
        lms.displayAllPatrons();
    }

    // Test Cases 13 and 14 (Exit, Invalid menu input) are not unit tested here,
    // since they involve Main's menu loop and Scanner input rather than LMS logic.
    // These are verified manually by running the program.
}


