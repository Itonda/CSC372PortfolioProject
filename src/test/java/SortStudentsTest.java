import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class SortStudentsTest {
    private StudentList studentList;
    private SortStudents sorter;

    @BeforeEach
    void setUp() {
        sorter = new SortStudents();
        studentList = new StudentList(); // This will add default students and increment nextStudentID

        // Clear any students from previous test runs if StudentList constructor adds them by default
        studentList.students.clear(); 

        // Reset the static ID counter BEFORE adding students for this specific test setup
        Student.resetNextStudentID(); 

        // Now, these students will get IDs starting from 100
        studentList.students.add(new Student("Charlie", "123 Street", 3.2)); // ID will be 100
        studentList.students.add(new Student("Alice", "456 Avenue", 3.8));   // ID will be 101
        studentList.students.add(new Student("Bob", "789 Road", 2.9));       // ID will be 102
    }

    @Test
    void testSortByNameAscending() {
        sorter.sortByNameAscending(studentList);

        List<String> expectedNames = Arrays.asList("Alice", "Bob", "Charlie");
        List<String> actualNames = studentList.students.stream()
                .map(Student::getName)
                .collect(Collectors.toList()); // Use collect(Collectors.toList()) for broader Java version compatibility

        assertEquals(expectedNames, actualNames, "Students should be sorted alphabetically by name (Ascending)");
    }

    @Test
    void testSortByNameDescending() {
        sorter.sortByNameDescending(studentList);

        List<String> expectedNames = Arrays.asList("Charlie", "Bob", "Alice");
        List<String> actualNames = studentList.students.stream()
                .map(Student::getName)
                .collect(Collectors.toList());

        assertEquals(expectedNames, actualNames, "Students should be sorted alphabetically by name (Descending)");
    }

    @Test
    void testSortByGPAAscending() {
        sorter.sortByGPAAscending(studentList);

        List<Double> expectedGPAs = Arrays.asList(2.9, 3.2, 3.8);
        List<Double> actualGPAs = studentList.students.stream()
                .map(Student::getGPA)
                .collect(Collectors.toList());

        assertEquals(expectedGPAs, actualGPAs, "Students should be sorted by GPA (Ascending)");
    }

    @Test
    void testSortByGPADescending() {
        sorter.sortByGPADescending(studentList);

        List<Double> expectedGPAs = Arrays.asList(3.8, 3.2, 2.9);
        List<Double> actualGPAs = studentList.students.stream()
                .map(Student::getGPA)
                .collect(Collectors.toList());

        assertEquals(expectedGPAs, actualGPAs, "Students should be sorted by GPA (Descending)");
    }

    @Test
    void testSortByIDAscending() {
        sorter.sortByIDAscending(studentList);

        // Expected IDs: Charlie (100), Alice (101), Bob (102)
        List<Integer> expectedIDs = Arrays.asList(100, 101, 102);
        List<Integer> actualIDs = studentList.students.stream()
                .map(Student::getStudentID) // Assuming getStudentID() is the correct getter
                .collect(Collectors.toList());

        assertEquals(expectedIDs, actualIDs, "Students should be sorted by ID (Ascending)");
    }

    @Test
    void testSortByIDDescending() {
        sorter.sortByIDDescending(studentList);

        // Expected IDs: Bob (102), Alice (101), Charlie (100)
        List<Integer> expectedIDs = Arrays.asList(102, 101, 100);
        List<Integer> actualIDs = studentList.students.stream()
                .map(Student::getStudentID) // Assuming getStudentID() is the correct getter
                .collect(Collectors.toList());

        assertEquals(expectedIDs, actualIDs, "Students should be sorted by ID (Descending)");
    }
}