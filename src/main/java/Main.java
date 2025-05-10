import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws InvalidStudentException {
        // Create a new Scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        // Create a new StudentListMenu object to display the menu and handle user input
        StudentListMenu studentListMenu = new StudentListMenu();

        studentListMenu.executeMainMenuSelection();
        
        // Close the scanner
        scanner.close();
    }
}