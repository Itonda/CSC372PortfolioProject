/*  This class uses a loop to gather input from the user to make a student object
 *  It uses a functional interface to process the input and validate it */
import java.util.Scanner;

public class InputStudent {
    /* Method to get student name from user 
     * @param prompt The prompt to display to the user
     * @param scanner The Scanner object to read user input */
    private String getNameInput(String prompt, Scanner scanner) {
        return getValidStringInput(prompt, scanner, "Name cannot be empty"); // Use the helper method to validate name input
    }
    /* Method to get student address from user
     * @param prompt The prompt to display to the user
     * @param scanner The Scanner object to read user input */
    private String getAddressInput(String prompt, Scanner scanner) {
        return getValidStringInput(prompt, scanner, "Address cannot be empty"); // Use the helper method to validate address input
    }
    /* Method to get student GPA from user 
     * @param prompt The prompt to display to the user
     * @param scanner The Scanner object to read user input */
    private Double getGpaInput(String prompt, Scanner scanner) {
        return getValidatedInput(prompt, scanner, rawInput -> { // Use the generic method to validate GPA input
            try {
                Double gpa = Double.parseDouble(rawInput); // Parse the input to a Double
                if (gpa < 0 || gpa > 4.0) { // Check if the GPA is within the valid range
                    throw new InvalidStudentException("GPA must be between 0.0 and 4.0"); // Throw an exception if out of range
                }
                return gpa;
            } catch (NumberFormatException e) { // Catch any parsing errors
                throw new InvalidStudentException("GPA must be a valid numeric value."); // Throw an exception if parsing fails
            }
        });
    }
    /* Method to get student information from user and create a Student object 
     * @param promptName The prompt for the student's name
     * @param promptAddress The prompt for the student's address
     * @param promptGPA The prompt for the sttudent's GPA
     * @param scanner The scanner object to read user input */
    public Student getStudentFromUser(String promptName, String promptAddress, String promptGPA, Scanner scanner) throws InvalidStudentException { 
        String name = getNameInput(promptName, scanner); // Get the student's name input
        String address = getAddressInput(promptAddress, scanner); // Get the student's address input
        Double gpa = getGpaInput(promptGPA, scanner); // Get the student's GPA input

        // Create a new student object using the constructor with parameters
        Student student = new Student(name, address, gpa);
        
        return student;
    }
    /* This method removes a student (by ID) from the user 
     * @param prompt The prompt to display to the user
     * @param scanner The Scanner object to read user input */
    public int getStudentIdFromUser(String prompt, Scanner scanner) throws InvalidStudentException { 
        return getValidatedInput(prompt, scanner, rawInput -> { // Use the generic method to validate student ID input
            try {
                int studentId = Integer.parseInt(rawInput); // Parse the input to an integer
                if (studentId < 0) { // Check if the student ID is valid
                    throw new InvalidStudentException("Student ID must be a positive number."); // Throw an exception if invalid
                }
                return studentId;
            } catch (NumberFormatException e) { // Catch any parsing errors
                throw new InvalidStudentException("Student ID must be a valid numeric value."); // Throw an exception if parsing fails
            }
        });
    }
    /* Functional interface for processing and validating input 
     * @param <T> The type of input to be processed
     * @param rawInput The raw input string from the user */
    @FunctionalInterface
    public interface InputProcessor<T> {
        T process(String rawInput) throws InvalidStudentException, NumberFormatException; 
    }
    /* Generic helper method to get and validate input 
     * @param <T> The type of input to be processed
     * @param prompt The prompt to display to the user
     * @param scanner The Scanner object to read user input
     * @param processor The InputProcessor to process and validate the input */
    public <T> T getValidatedInput(String prompt, Scanner scanner, InputProcessor<T> processor) { 
       
        while (true) { // Loop until valid input is received
            try {
                System.out.print(prompt); // Display the prompt to the user
                String rawInput = scanner.nextLine().strip(); // Get user input and strip leading/trailing whitespace

                return processor.process(rawInput); // Process and validate the input using the provided processor

            } catch (InvalidStudentException e) {
                System.out.println("Invalid input. " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }                
        }    
    }
    /* Helper method to get a string input with validation
     * @param prompt The prompt to display to the user
     * @param scanner The Scanner object to read user input
     * @param errorMessage The error message to display if the input is invalid */
    public String getValidStringInput(String prompt, Scanner scanner, String errorMessage) {
        return getValidatedInput(prompt, scanner, rawInput -> { // Use the generic method to validate string input
            if (rawInput.isEmpty()) { // Check if the input is empty
                throw new InvalidStudentException(errorMessage); // Throw an exception if empty
            }
            return rawInput;
        });
    }
}