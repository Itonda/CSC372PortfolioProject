// This class defines a custom exception called InvalidStudentException.
public class InvalidStudentException extends Exception {
    public InvalidStudentException(String message) {
        super(message); // Call the constructor of the superclass (Exception) with the message
    }
}