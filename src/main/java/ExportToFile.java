/* This class exports the student list to a txt file 
 * It creates a directory called Temp in the user's home directory
 * It creates a file with the name passed as a parameter
 * It writes the content passed as a parameter to the file */
import java.io.File;
import java.io.PrintWriter;

public class ExportToFile {
    /* This method handles the error when exporting the student list to a file
    * @param errorMessage The error message to be displayed
    * @param errorTitle The title of the error message */
    private static void handleExportError(String errorMessage, String errorTitle) {
        System.err.println("Error: " + errorMessage);
        
    }
    /* This method exports the student list to a text file
     * @param content The content to be written to the file
     * @param filename The name of the file to be created */
    public static void exportToTextFile(String content, String filename) {
        String directoryPath = System.getProperty("user.home") 
                                + File.separator + "Temp" 
                                + File.separator; // Define the directory path as Temp in the user's home directory
        File directory = new File(directoryPath); // Create a File object for the directory

        if (!directory.exists()) { // Check if the directory exists
            if (!directory.mkdirs()) { // Check if the directory was created successfully
                handleExportError("Could not create directory: " + 
                                directoryPath, "Directory Error"); // Handle the error if directory creation fails
                return; 
            }
        }

        String filePath = directoryPath + filename; // Define the file path

        try (PrintWriter writer = new PrintWriter(filePath)) { // Create a PrintWriter object to write to the file
                writer.println(content); // Write the content to the file
                System.out.println("Exported to " + filePath); // Show a success message
                // Show a success message
            } catch (Exception e) { // Catch any exceptions that occur during file writing
                handleExportError("Error writing to file: " + e.getMessage(), "File Error"); // Handle the error if file writing fails
            }
        }
}