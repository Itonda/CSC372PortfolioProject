import java.util.ListIterator; // Import ListIterator

/* This class searches the student list by ID number
 * It uses a linear search algorithm to find the student
 * It returns the index of the student in the list */
public class SearchStudent {
    /* This method searches for a student by ID using binary search
     * @param studentList The list of students to search in
     * @param studentId The ID of the student to search for */
    public int searchByID(StudentList studentList, int studentId) {
        if (studentList == null || studentList.students == null) { // Check if the student list is null
            return -1;
        }
        ListIterator<Student> iterator = studentList.students.listIterator(); // Create a ListIterator to traverse the list
        while (iterator.hasNext()) { // While there are more elements in the list
            Student currentStudent = iterator.next(); // Get the next student in the list
            if (currentStudent != null && currentStudent.getStudentID() == studentId) { // Check if the current student matches the ID
                return iterator.previousIndex(); // Get the index of the element just returned by next()
            }
        }
        return -1; // Student not found
    }
}
