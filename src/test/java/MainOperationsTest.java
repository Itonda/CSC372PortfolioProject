import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// Assuming your classes are in the default package or appropriate imports are added
// e.g., import com.yourpackage.Student;

public class MainOperationsTest {

    private StudentList studentList;
    private SortStudents sorter;
    private ExportStudentList exporter;

    @TempDir
    Path tempDir; // JUnit Jupiter will provide a temporary directory

    @BeforeEach
    void setUp() {
        Student.resetNextStudentID(); // Reset ID counter for consistent test runs
        studentList = new StudentList();
        // Ensure StudentList constructor doesn't add default students, or clear them
        studentList.students.clear(); 
        sorter = new SortStudents();
        exporter = new ExportStudentList();
    }

    @Test
    void testAddTwentyStudentsSortByNameDescendingAndExport() throws IOException {
        // 1. Add 20 students
        List<String> studentNames = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String name = "Student" + String.format("%02d", i); // e.g., Student00, Student01
            studentNames.add(name);
            studentList.addToList(new Student(name, "Address " + i, 2.0 + (i % 20) / 10.0));
        }

        // 2. Sort them by name in descending order
        sorter.sortByNameDescending(studentList);

        // 3. Verify the list is sorted correctly in memory
        List<String> expectedSortedNames = studentNames.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        List<String> actualSortedNames = studentList.students.stream()
                .map(Student::getName)
                .collect(Collectors.toList());

        assertEquals(expectedSortedNames, actualSortedNames, "Students should be sorted by name in descending order.");

        // 4. Export the list
        // Modify ExportStudentList to allow specifying the directory for easier testing
        // For now, we'll assume it writes to a predictable location or we test its default
        String testFileName = "studentList.txt";
        
        // We'll use the tempDir provided by JUnit for the export to keep tests clean
        Path exportFilePath = tempDir.resolve(testFileName);

        // To use tempDir, ExportStudentList needs to be adaptable.
        // If ExportStudentList is not adaptable, this part needs to match its fixed behavior.
        // For this example, let's assume we can make ExportStudentList write to a specific path.
        // If not, you'd have to construct the path ExportStudentList uses by default.
        
        // Simulating export (actual call to your exporter)
        // exporter.exportToTextFile(exportFilePath.toString(), studentList.toString());
        // For the current ExportStudentList, it creates "Temp" in user.home.
        // We'll test that file creation.

        String userHome = System.getProperty("user.home");
        Path expectedExportDir = Path.of(userHome, "Temp");
        Path expectedExportFile = expectedExportDir.resolve(testFileName);

        // Ensure the "Temp" directory exists for the test, as ExportStudentList creates it.
        // Files.createDirectories(expectedExportDir); // ExportStudentList should do this.

        exporter.exportToTextFile(testFileName, studentList.toString());


        // 5. Verify the file was created
        assertTrue(Files.exists(expectedExportFile), "Exported file should exist at: " + expectedExportFile.toString());
        assertTrue(Files.size(expectedExportFile) > 0, "Exported file should not be empty.");

        // Optional: Verify content (more involved, requires reading the file)
        // String fileContent = Files.readString(expectedExportFile);
        // assertTrue(fileContent.contains("Student19"), "File content should contain expected student data.");
        // assertTrue(fileContent.startsWith(studentList.toString()), "File content should match StudentList.toString()");


        // Cleanup: Delete the test file and directory if needed, though @TempDir handles its own lifecycle for its path.
        // Files.deleteIfExists(expectedExportFile);
        // If "Temp" was created just for this, consider deleting it.
        // However, typical unit tests try to avoid altering user's home directory directly if possible.
        // Using @TempDir is better if ExportStudentList can be adapted.
    }
}