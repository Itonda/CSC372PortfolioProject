import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
// Assuming ExportToFile is in the same package or imported
// Assuming Student is in the same package or imported
// Assuming StudentList is in the same package or imported

public class MainOperationsTest {

    private StudentList studentList;
    // private ExportToFile exporter; // Removed as ExportToFile.exportToTextFile is static

    @TempDir
    Path tempDir; // JUnit Jupiter will provide a temporary directory

    @BeforeEach
    void setUp() {
        Student.resetNextStudentID(); // Reset ID counter. IDs for new students will start from 100.
        studentList = new StudentList(); // Creates an empty list (assuming StudentList constructor doesn't add defaults)
    }

    // Assuming testAddTwentyStudentsSortByNameDescendingAndExport() might exist here, it's left untouched.
    // Only rewriting testRemoveFirstAndLastStudentsWithTiming as requested.

    @Test
    void testRemoveFirstAndLastStudentsWithTiming() {
        int numberOfStudentsToAdd = 50000; // Change this to test with different numbers (e.g., 0, 1, 2, 500)

        // 1. Add students to the initially empty list
        for (int i = 0; i < numberOfStudentsToAdd; i++) {
            // Student IDs will be 100, 101, ..., 100 + numberOfStudentsToAdd - 1
            studentList.addToList(new Student("Student" + i, "Address" + i, 2.5 + (i % 10) * 0.1));
        }
        assertEquals(numberOfStudentsToAdd, studentList.getStudentList().size(), "List size should match number of students added.");

        if (numberOfStudentsToAdd == 0) {
            System.out.println("No students added. Skipping removal tests.");
            assertTrue(studentList.isEmpty(), "List should be empty if no students were added.");
            return; // Exit test if no students were added
        }

        // 2. Identify IDs for removal
        // Since Student.resetNextStudentID() was called and list was empty, IDs start from 100
        int firstStudentIdToRemove = 100; // ID of the first student added in this test
        int lastStudentIdToRemove = 100 + numberOfStudentsToAdd - 1; // ID of the last student added in this test

        // Ensure these students actually exist before trying to remove them
        // StudentList.searchById(id) returns index, or -1 if not found.
        assertTrue(studentList.searchById(firstStudentIdToRemove) != -1, "First student (ID: " + firstStudentIdToRemove + ") should exist before removal.");
        if (numberOfStudentsToAdd > 1) { // Only check last if it's different from first
             assertTrue(studentList.searchById(lastStudentIdToRemove) != -1, "Last student (ID: " + lastStudentIdToRemove + ") should exist before removal.");
        } else { // numberOfStudentsToAdd == 1
            assertEquals(firstStudentIdToRemove, lastStudentIdToRemove, "For a single student, first and last ID should be the same.");
        }

        // 3. Remove the first student in the list and time it
        System.out.println("Attempting to remove first student (ID: " + firstStudentIdToRemove + ")");
        long startTimeRemoveFirst = System.nanoTime();
        boolean firstRemoved = studentList.removeStudentByID(firstStudentIdToRemove);
        long endTimeRemoveFirst = System.nanoTime();
        long durationRemoveFirstNanos = endTimeRemoveFirst - startTimeRemoveFirst;

        assertTrue(firstRemoved, "First student (ID: " + firstStudentIdToRemove + ") should be removed.");
        assertEquals(numberOfStudentsToAdd - 1, studentList.getStudentList().size(), "List size should decrease by 1 after first removal.");
        assertEquals(-1, studentList.searchById(firstStudentIdToRemove), "Removed first student (ID: " + firstStudentIdToRemove + ") should no longer be found (searchById should return -1).");
        System.out.println("Time to remove first student (ID: " + firstStudentIdToRemove + ") from " + numberOfStudentsToAdd + " students: " +
                           durationRemoveFirstNanos + " ns (" + durationRemoveFirstNanos / 1_000_000.0 + " ms)");

        // 4. Remove the last student in the list and time it
        if (numberOfStudentsToAdd == 1) {
            // The only student was already removed as the "first" student.
            System.out.println("Only one student was present and it has been removed. No 'last' student to remove separately.");
            assertTrue(studentList.isEmpty(), "List should be empty after removing the only student.");
        } else { // numberOfStudentsToAdd > 1, so there's a distinct last student to remove (or was before first removal)
            long currentSizeBeforeLastRemoval = numberOfStudentsToAdd - 1; // Size after the first removal
            System.out.println("Attempting to remove last student (ID: " + lastStudentIdToRemove + ")");
            long startTimeRemoveLast = System.nanoTime();
            boolean lastRemoved = studentList.removeStudentByID(lastStudentIdToRemove);
            long endTimeRemoveLast = System.nanoTime();
            long durationRemoveLastNanos = endTimeRemoveLast - startTimeRemoveLast;

            assertTrue(lastRemoved, "Last student (ID: " + lastStudentIdToRemove + ") should be removed.");
            // After removing two students in total from the initial 'numberOfStudentsToAdd'
            assertEquals(numberOfStudentsToAdd - 2, studentList.getStudentList().size(), "List size should decrease by 2 after both removals.");
            assertEquals(-1, studentList.searchById(lastStudentIdToRemove), "Removed last student (ID: " + lastStudentIdToRemove + ") should no longer be found (searchById should return -1).");
            System.out.println("Time to remove last student (ID: " + lastStudentIdToRemove + ") from " + currentSizeBeforeLastRemoval + " students: " +
                               durationRemoveLastNanos + " ns (" + durationRemoveLastNanos / 1_000_000.0 + " ms)");
        }
    }
}  