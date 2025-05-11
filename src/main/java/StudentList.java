import java.util.Collections;
import java.util.Comparator; 
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
/* This class stores student objects in a list
 * It uses a linked list to store the students
 * It has methods to add, remove, and print the list of students */
public class StudentList {
    // Implement linked list
    private LinkedList<Student> students = new LinkedList<>();

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
    /* This method returns an unmodifiable view of the list of students for outside access */
    public List<Student> getStudentList() {
        return Collections.unmodifiableList(students);
    }
    /* This method sorts the list of students using a comparator
     * @param comparator The comparator to be used for sorting */
    private void sort(Comparator<Student> comparator) {
        if (this.students != null) { // 'this.students' is the actual mutable list
            this.students.sort(comparator);
        }
    }
    // This method sorts the list of students by name in ascending order
    public void sortByNameAscending() {
        sort(Comparator.comparing(Student::getName)); // Sort by name in ascending order using the comparator
    }
    // This method sorts the list of students by name in descending order
    public void sortByNameDescending() {
        sort(Comparator.comparing(Student::getName).reversed());
    }
    // This method sorts the list of students by GPA in ascending order
    public void sortByGpaAscending() {
        sort(Comparator.comparing(Student::getGPA));
    }
    // This method sorts the list of students by GPA in descending order
    public void sortByGpaDescending() {
        sort(Comparator.comparing(Student::getGPA).reversed());
    }
    // This method sorts the list of students by ID in ascending order
    public void sortByIdAscending() {
        sort(Comparator.comparingInt(Student::getStudentID));
    }
    // This method sorts the list of students by ID in descending order
    public void sortByIdDescending() {
        sort(Comparator.comparingInt(Student::getStudentID).reversed());
    }
    // This method prints the list of students to the console 
    public void printList() {
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }
    /* This method searches for a student by ID using linear search
     * @param studentList The list of students to search in
     * @param studentId The ID of the student to search for */
    public int searchById(int studentId) {
        if (this.students == null) { // Check if the student list is null
            return -1;
        }
        ListIterator<Student> iterator = this.students.listIterator(); // Create a ListIterator to traverse the list
        while (iterator.hasNext()) { // While there are more elements in the list
            Student currentStudent = iterator.next(); // Get the next student in the list
            if (currentStudent != null && currentStudent.getStudentID() == studentId) { // Check if the current student matches the ID
                return iterator.previousIndex(); // Get the index of the element just returned by next()
            }
        }
        return -1; // Student not found
    }
    public boolean removeStudentByID(int studentID) {
        // Search for the student by ID
        int index = searchById(studentID);
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