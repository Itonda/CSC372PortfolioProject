import java.util.InputMismatchException;
import java.util.Scanner;
/* This class handles the menu for the student list
 * It provides options to add, remove, print, sort, and export the student list */
public class StudentListMenu {
    private StudentList studentList = new StudentList();
    private InputStudent inputStudent = new InputStudent();
    private int selection = 0;
    /* This method diplays the menu for sorting the student list
     * It shows the options available to the user */
    private void displaySortMenu() {
        System.out.println("\nChoose Your Sort Method:");
        System.out.println("1. Sort by Name (Ascending)");
        System.out.println("2. Sort by Name (Descending)");
        System.out.println("3. Sort by GPA (Ascending)");
        System.out.println("4. Sort by GPA (Descending)");
        System.out.println("5. Sort by ID (Ascending)");
        System.out.println("6. Sort by ID (Descending)");
        System.out.println("7. Exit to Main Menu");
    }
    /* This method displays the main menu to the user
     * It shows the options available to the user */
    private void displayMainMenu() {
        System.out.println("\n----Main Menu----");
        System.out.println("1. Add Student");
        System.out.println("2. Remove Student");
        System.out.println("3. Print Student List");
        System.out.println("4. Sort Student List");
        System.out.println("5. Export Student List");
        System.out.println("6. Exit");
        
    }
    /* This method gets the user's selection from the menu
     * It reads the input and stores it in the selection variable */
    public void getSelection(Scanner scanner) {
        System.out.print("\nEnter your selection: ");

        selection = scanner.nextInt(); // Read the user's selection
        scanner.nextLine(); // Consume the newline character left by nextInt()
    }
    /* Helper method for yes/no confirmation 
     * @param prompt The prompt to display to the user */
    private boolean getYesNoConfirmation(String prompt, Scanner scanner) throws InvalidStudentException {
        System.out.print(prompt);
        String confirmation = scanner.nextLine().trim(); // Read user input and trim whitespace

        if (confirmation.equalsIgnoreCase("yes")) { // Check if the input is "yes"
            return true;
        } else if (confirmation.equalsIgnoreCase("no")) { // Check if the input is "no"
            return false;
        } else {
            throw new InvalidStudentException("\nInvalid input. Please enter 'yes' or 'no'."); // Throw an exception for invalid input
        }
    }
    /* This method is used to confirm the removal of the student from the list
     * It displays the student ID and name of the student to be removed
     * and asks the user to confirm the removal
     * @param student The student to be removed */
    private boolean confirmRemoveStudent (Student student, Scanner scanner) throws InvalidStudentException {
        System.out.println("\nAre you sure you want to remove the following student?");
        System.out.println("ID: " + student.getStudentID());
        System.out.println("Name: " + student.getName());

        return getYesNoConfirmation("Enter 'yes' to confirm or 'no' to cancel: ", scanner);
    }
    /* This method confirms if a user wants to add a student
     * Since the studentList.addToList method does not provide an escape
     * give the user a chance to exit switch case 1 */
    private boolean confirmAddStudent (Scanner scanner) throws InvalidStudentException {
        return getYesNoConfirmation("\nAre you sure you want to add a student? (yes/no): ", scanner);
    }
    /* This method executes the sort menu selection
     * It sorts the student list based on the user's choice */
    public void executeSortMenuSelection(Scanner scanner) throws InvalidStudentException {
        while (true) { // Loop until the user chooses to exit
            try {
                displaySortMenu(); // Display the sort menu
                getSelection(scanner); // Get the user's selection
                
                switch (selection) { // Check the user's selection and execute the corresponding action
                    case 1: // Selection 1: Sort by name ascending
                        studentList.sortByNameAscending(); // Use the studentList class to sort the list for each case
                        System.out.println("\nStudent list sorted by name in ascending order.");
                        break;
                    case 2: // Selection 2: Sort by name descending
                        studentList.sortByNameDescending(); 
                        System.out.println("\nStudent list sorted by name in descending order.");
                        break;
                    case 3: // Selection 3: Sort by GPA ascending
                        studentList.sortByGpaAscending(); 
                        System.out.println("\nStudent list sorted by GPA in ascending order.");
                        break;
                    case 4: // Selection 4: Sort by GPA descending
                        studentList.sortByGpaDescending(); 
                        System.out.println("\nStudent list sorted by GPA in descending order.");
                        break;
                    case 5: // Selection 5: Sort by ID ascending
                        studentList.sortByIdAscending(); 
                        System.out.println("\nStudent list sorted by ID in ascending order.");
                        break;
                    case 6: // Selection 6: Sort by ID descending
                        studentList.sortByIdDescending(); 
                        System.out.println("\nStudent list sorted by ID in descending order.");
                        break;
                    case 7: // Selection 7: Exit to main menu
                        System.out.println("\nExiting to main menu."); 
                        return; // Exit to main menu
                    default:
                        System.out.println("\nInvalid selection. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }
    public void executeMainMenuSelection() throws InvalidStudentException {
        try (Scanner scanner = new Scanner(System.in)) { // Create a new Scanner object for user input
            while (true) { // Loop until the user chooses to exit
                try {
                    displayMainMenu(); // Display the menu and get the user's selection
                    getSelection(scanner); // Get the user's selection

                    switch (selection) { // Check the user's selection and execute the corresponding action
                        case 1: // Selection 1: Add Student
                            if (confirmAddStudent(scanner)) { // Confirm if the user wants to add a student
                                studentList.addToList(inputStudent.getStudentFromUser("\nEnter student name: ", 
                                                                                "Enter student address: ", 
                                                                                "Enter student GPA: ", 
                                                                                scanner)); // Get student details from user
                                System.out.println("\nStudent added successfully.");
                            break;
                            } else {
                                System.out.println("\nStudent addition canceled."); // If the user does not confirm, exit the case
                                break;
                            }
                        case 2: // Selection 2: Remove Student - Provide exit condition (0)
                            int studentId = inputStudent.getStudentIdFromUser("\nEnter student ID to remove (enter 0 for main menu): ", 
                                                                                scanner); // Get the student ID or exit input from user

                            if (studentId == 0) { // Check for exit condition
                                System.out.println("\nExiting to main menu.");
                                break; // If exit condition: exit to main menu
                            }
                            
                            int index = studentList.searchById(studentId); // Search for the student by ID and assign the index

                            if (index != -1) { // If the student is found
                                Student studentToRemove = studentList.getStudentList().get(index); // Get the student object    
                                if (confirmRemoveStudent(studentToRemove, scanner)) { // Confirm removal
                                    studentList.removeStudentByID(studentId); // Remove the student from the list
                                    System.out.println("\nStudent removed successfully.");
                                } else {
                                    System.out.println("\nStudent removal canceled.");
                                }
                            } else {
                                System.out.println("\nStudent not found."); // If not found, display not found message
                            }
                            break;  
                        case 3: // Selection 3: Print Student List
                            if (studentList.isEmpty()) { // Check if the list is empty
                                System.out.println("\nThe student list is empty."); // If empty, display message
                            } else {
                                System.out.println("\nStudent List:"); // If not empty, display the student list
                            }
                            studentList.printList(); 
                            break;
                        case 4: // Selection 4: Sort Student List
                            executeSortMenuSelection(scanner); // Call the method to execute sort menu selection
                            break;
                        case 5: // Selection 5: Export Student List
                            System.out.println("\nExporting the student list...");
                            ExportToFile.exportToTextFile(studentList.toString(), "studentList.txt"); // Use the ExportStudentList class to export the list
                            break;
                        case 6: // Selection 6: Exit
                            System.out.println("\nExiting the program.");
                            return; // Exit the program
                        default:
                            System.out.println("\nInvalid selection. Please try again."); // If invalid selection, display message
                    }  
                } catch (InputMismatchException e) { // Catch any input mismatch exceptions
                    System.out.println("\nInvalid input. Please enter a number.");
                    scanner.nextLine(); // Clear the invalid input
                }
            }
        }   
    }
}
