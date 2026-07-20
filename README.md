Library Management System (LMS)

A console-based Java application for managing library patron records. Built as part of a Java software development course, the LMS allows librarians to add, remove, search, and display patron records through a simple text-based menu.


Features


Load patron records from a formatted text file
Manually add new patrons through the console
Remove a patron by their 7-digit ID
Search for a specific patron by their 7-digit ID
Display all patrons currently in the system
Input validation with re-prompting on invalid entries
JUnit test suite covering all core functionality



Tech Stack


Language: Java
IDE: IntelliJ IDEA
Testing: JUnit 4
Version Control: Git / GitHub
Build Output: Executable JAR file



Project Structure

LibraryManagementSystem/
├── src/
│   ├── Patron.java          # Data model for a single patron
│   ├── LMS.java             # Core logic: add, remove, search, load, display
│   ├── Main.java            # Console menu and user interaction
│   ├── LMSTest.java         # JUnit test suite (12 test cases)
│   ├── test_patrons.txt              # Valid patron test file
│   ├── test_patrons_bad_id.txt       # Test file with invalid ID
│   └── test_patrons_bad_fine.txt     # Test file with out-of-range fine
└── out/
    └── artifacts/
        └── LibraryManagementSystem.jar


Patron Data Format

Each patron has the following fields:

FieldTypeConstraintIDStringExactly 7 digitsNameStringAny textAddressStringAny textFine AmountdoubleBetween $0.00 and $250.00

Input File Format

When loading from a file, each line represents one patron using this format:

ID-Name-Address-FineAmount

No dollar sign on the fine amount. Example:

1245789-Sarah Jones-1136 Gorden Ave. Orlando, FL 32822-40.54
3256897-Mason Arby-6060 Saginaw St. Casselberry, FL 34852-0
4567891-Avery Jones-1919 Pine Lance Blvd. Oviedo, FL 32478-1.36


How to Run

Option 1 — Run from IntelliJ IDEA


Open the project in IntelliJ IDEA
Navigate to Main.java
Click the green Run arrow next to the main() method



Note: If you plan to use the file import feature, run from a real terminal instead of IntelliJ's built-in console — pasting file paths into IntelliJ's console can cause input stream issues on macOS.



Option 2 — Run from Terminal (recommended for file import)

bashcd path/to/LibraryManagementSystem/out/artifacts/LibraryManagementSystem_jar
java -jar LibraryManagementSystem.jar


Menu Options

===== Library Management System =====
1. Load patrons from file
2. Add a patron manually
3. Remove a patron
4. Search for a patron
5. Display all patrons
6. Exit


Running the Tests


Open LMSTest.java in IntelliJ IDEA
Click the green Run arrow next to the class name to run all 12 tests at once
All tests should pass with a green checkmark


The test suite covers:


Loading a valid file
Loading a file with an invalid ID (skipped)
Loading a file with an out-of-range fine (skipped)
Adding a patron successfully
Rejecting a duplicate ID
Removing a patron by valid ID
Attempting to remove a non-existent patron
Searching by a valid ID
Searching by a non-existent ID
Displaying all patrons when the list is empty
Displaying all patrons with multiple records



Known Behavior


Data is stored in memory only — all patron records are lost when the program exits. There is no persistent storage between sessions.
The file import feature splits each line on the first 3 dashes only, so addresses that contain dashes are handled correctly.
On macOS, IntelliJ IDEA may need Downloads folder access granted in System Settings → Privacy & Security → Files and Folders before it can read files from that directory.



Author

Ralph Alexandre
github.com/ralphdevlib
