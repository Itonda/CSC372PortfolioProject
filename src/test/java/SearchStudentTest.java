import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Make sure your Student, StudentList, and SearchStudent classes are accessible.
// If they are in src/main/java under a package, you might need to import them,
// e.g., import com.example.Student;

public class SearchStudentTest {

    @Test
    void searchByID_whenStudentExists_thenReturnsStudentIndex() {
        // Arrange: Create a new StudentList object
        StudentList studentList = new StudentList();
        
        // Add some students to the list.
        // Assuming your Student class assigns IDs starting from 100 and incrementing:
        // Alice will get ID 100
        // Bob will get ID 101
        // Charlie will get ID 102
        studentList.addToList(new Student("Alice", "123 Main St", 3.5));
        studentList.addToList(new Student("Bob", "456 Elm St", 3.8));
        studentList.addToList(new Student("Charlie", "789 Oak St", 3.2)); // This student has ID 102
        
        int searchID = 102; // The ID we are searching for
        SearchStudent searchStudent = new SearchStudent();
        
        // Act: Search for the student by ID
        int foundStudentId = searchStudent.searchByID(studentList, searchID);
        
        // Assert: Check if the student was found.
        // If found, the method should return the index of the student.
        assertEquals(2, foundStudentId, "Student with ID " + searchID + " should be found at index 2.");
        
    }

    // It's also good practice to add tests for other scenarios, for example:
    @Test
    void searchByID_whenStudentDoesNotExist_thenReturnsNegativeOne() {
        // Arrange
        StudentList studentList = new StudentList();
        studentList.addToList(new Student("Alice", "123 Main St", 3.5)); // ID 100
        studentList.addToList(new Student("Bob", "456 Elm St", 3.8));   // ID 101
        
        int searchIDNotInList = 105; // An ID that doesn't exist
        SearchStudent searchStudent = new SearchStudent();
        
        // Act
        int foundStudentId = searchStudent.searchByID(studentList, searchIDNotInList);
        
        // Assert
        assertEquals(-1, foundStudentId, "Student with ID " + searchIDNotInList + " should not be found, and method should return -1.");
    }
}