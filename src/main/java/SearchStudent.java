// This class uses a binary search algorithm to find a student by ID
public class SearchStudent {
    SortStudents sortStudents = new SortStudents(); // Instance of SortStudents to sort the list
    /* This method searches for a student by ID using binary search
     * @param studentList The list of students to search in
     * @param studentId The ID of the student to search for */
    public int searchByID(StudentList studentList, int studentId) {
        int left = 0; // Left index of the search range
        int right = studentList.students.size() - 1; // Right index of the search range
        // Sort the list before searching
        sortStudents.sortByIDAscending(studentList); // Sort the list by ID in ascending order
        while (left <= right) { // While there are elements to search
            int mid = left + (right - left) / 2; // Calculate the middle index

            if (studentList.students.get(mid).getStudentID() == studentId) { // If the middle element is the target
                return mid; // Return the index of the found element
            }
            if (studentList.students.get(mid).getStudentID() < studentId) { // If the target is greater than the middle element
                left = mid + 1; // Narrow down the search range to the right half
            } else { // If the target is less than the middle element
                right = mid - 1; // Narrow down the search range to the left half
            }
        }
        return -1; // Return -1 if not found
    }
}
