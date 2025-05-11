import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class ExportToFileTest {

    @Test
    void testExportToTextFile_Success() throws IOException {
        String testContent = "Student1: Alice, GPA: 3.8\nStudent2: Bob, GPA: 3.2";
        String testFilename = "testExport.txt";

        // Get expected file path
        String expectedFilePath = System.getProperty("user.home") + File.separator + "Temp" + File.separator + testFilename;

        // Call the export method
        ExportToFile.exportToTextFile(testContent, testFilename);

        // Verify the file exists
        File exportedFile = new File(expectedFilePath);
        assertTrue(exportedFile.exists(), "File should be created successfully.");

        // Verify the file content
        String actualContent = Files.readString(Path.of(expectedFilePath));
        assertEquals(testContent + System.lineSeparator(), actualContent, "File content should match expected output.");

        // Cleanup: Delete test file after verification
        Files.deleteIfExists(Path.of(expectedFilePath));
    }

    @Test
    void testExportToTextFile_ErrorHandling() {
        String invalidFilename = "/invalid/path/test.txt"; // Invalid path
        String testContent = "Student1: Alice, GPA: 3.8";

        // Expect no exception, just error logging (manual verification needed)
        ExportToFile.exportToTextFile(testContent, invalidFilename);

        // The file should not exist since the path is invalid
        File exportedFile = new File(invalidFilename);
        assertFalse(exportedFile.exists(), "File should not be created due to invalid path.");
    }
}