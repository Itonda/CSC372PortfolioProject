
import java.util.LinkedList;
import java.util.stream.Collectors;
/* This class stores student objects in a list
 * It uses a linked list to store the students
 * It has methods to add, remove, and print the list of students */
public class StudentList {
    // Implement linked list
    public LinkedList<Student> students = new LinkedList<>();
    public SearchStudent searchStudent = new SearchStudent();

    /*----TESTING - ADD 10 TEST STUDENTS----
     * This method can safely be removed in production */
    public StudentList() {
        students.add(new Student("John Doe", "123 Main St", 3.5));
        students.add(new Student("Jane Smith", "456 Elm St", 3.8));
        students.add(new Student("Alice Johnson", "789 Oak St", 3.2));
        students.add(new Student("Bob Brown", "101 Pine St", 3.6));
        students.add(new Student("Charlie Davis", "202 Maple St", 3.9));
        students.add(new Student("David Wilson", "303 Cedar St", 3.4));
        students.add(new Student("Eve Thompson", "404 Birch St", 3.7));
        students.add(new Student("Frank Garcia", "505 Spruce St", 3.1));
    }
    /* This method adds a student object to the linked list
     * It checks if the list is null and creates a new list if it is
     * @param student The student object to be added to the list */
    public void addToList(Student student) {
        students.add(student);
    }
    public void printList() {
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }
    public boolean removeStudentByID(int studentID) {
        // Search for the student by ID
        int index = searchStudent.searchByID(this, studentID);
        if (index != -1) { // If the student is found
            students.remove(index); // Remove the student from the list
            return true; // Return true to indicate successful removal
        }
        return false; // Return false if the student was not found
    }
    // This method checks if the list is empty
    public boolean isEmpty() {
        return students.isEmpty();
    }
    // This method builds a string representation of the student list using streams
    @Override
    public String toString() {
        return students.stream() // Create a stream from the list of students
                    .map(Student::toString) // Use map to convert each student to a string with Student's toString method
                    .collect(Collectors.joining("\n")); // Use collect to join the strings with new line characters
    }
}
