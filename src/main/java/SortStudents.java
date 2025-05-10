import java.util.Comparator; // Import Comparator

// This class sorts the students in the list by name
public class SortStudents {

    // Private helper method to perform the sort
    private void sort(StudentList studentList, Comparator<Student> comparator) {
        if (studentList != null && studentList.students != null) {
            studentList.students.sort(comparator);
        }
    }
    /* This method sorts the students in the list by name in ascending order.
     * @param studentList The list of students to be sorted */
    public void sortByNameAscending(StudentList studentList) {
        sort(studentList, Comparator.comparing(Student::getName));
    }
    /* This method sorts the students in the list by name in descending order.
     * @param studentList The list of students to be sorted */
    public void sortByNameDescending(StudentList studentList) {
        sort(studentList, Comparator.comparing(Student::getName).reversed());
    }
    /* This method sorts the students in the list by GPA in ascending order.
     * @param studentList The list of students to be sorted */
    public void sortByGPAAscending(StudentList studentList) {
        sort(studentList, Comparator.comparing(Student::getGPA));
    }
    /* This method sorts the students in the list by GPA in descending order.
     * @param studentList The list of students to be sorted */
    public void sortByGPADescending(StudentList studentList) {
        sort(studentList, Comparator.comparing(Student::getGPA).reversed());
    }
    /* This method sorts the students in the list by ID number in ascending order.
     * @param studentList The list of students to be sorted */
    public void sortByIDAscending(StudentList studentList) {
        // Using Comparator.comparingInt for primitive int ID for efficiency and clarity
        sort(studentList, Comparator.comparingInt(Student::getStudentID));
    }
    /* This method sorts the students in the list by ID number in descending order.
     * @param studentList The list of students to be sorted */
    public void sortByIDDescending(StudentList studentList) {
        sort(studentList, Comparator.comparingInt(Student::getStudentID).reversed());
    }
}
