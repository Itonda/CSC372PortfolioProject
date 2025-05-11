import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentListSortTest {

    private StudentList studentList;
    private List<Student> testStudents; // To hold the original unsorted list for comparison

    @BeforeEach
    void setUp() {
        Student.resetNextStudentID(); // Reset ID counter for consistent student IDs
        studentList = new StudentList(); // Creates an empty list
        testStudents = new ArrayList<>();

        // Add 20 unique students with varying names, GPAs. IDs will be 100-119.
        // Names are in reverse alphabetical order to make sorting more evident.
        // GPAs are varied.
        // Addresses are simple.
        char nameChar = 'T'; // Start from 'T' down to 'A'
        for (int i = 0; i < 20; i++) {
            String name = "Student" + (char)(nameChar - i);
            // Vary GPAs: 2.0, 2.1, ..., 3.9, then repeat
            double gpa = 2.0 + (i % 20) * 0.1; 
            // Ensure GPA is capped at 4.0 if the above calculation exceeds it
            gpa = Math.min(gpa, 4.0); 
            Student student = new Student(name, "Address " + (char)(nameChar - i), gpa);
            testStudents.add(student);
            studentList.addToList(student);
        }
    }

    @Test
    void testSortByNameAscending() {
        studentList.sortByNameAscending();
        List<String> expectedNames = testStudents.stream()
                .map(Student::getName)
                .sorted()
                .collect(Collectors.toList());
        List<String> actualNames = studentList.getStudentList().stream()
                .map(Student::getName)
                .collect(Collectors.toList());
        assertEquals(expectedNames, actualNames, "Students should be sorted by name in ascending order.");
    }

    @Test
    void testSortByNameDescending() {
        studentList.sortByNameDescending();
        List<String> expectedNames = testStudents.stream()
                .map(Student::getName)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        List<String> actualNames = studentList.getStudentList().stream()
                .map(Student::getName)
                .collect(Collectors.toList());
        assertEquals(expectedNames, actualNames, "Students should be sorted by name in descending order.");
    }

    @Test
    void testSortByGpaAscending() {
        studentList.sortByGpaAscending();
        List<Double> expectedGpas = testStudents.stream()
                .map(Student::getGPA)
                .sorted()
                .collect(Collectors.toList());
        List<Double> actualGpas = studentList.getStudentList().stream()
                .map(Student::getGPA)
                .collect(Collectors.toList());
        assertEquals(expectedGpas, actualGpas, "Students should be sorted by GPA in ascending order.");
    }

    @Test
    void testSortByGpaDescending() {
        studentList.sortByGpaDescending();
        List<Double> expectedGpas = testStudents.stream()
                .map(Student::getGPA)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        List<Double> actualGpas = studentList.getStudentList().stream()
                .map(Student::getGPA)
                .collect(Collectors.toList());
        assertEquals(expectedGpas, actualGpas, "Students should be sorted by GPA in descending order.");
    }

    @Test
    void testSortByIdAscending() {
        studentList.sortByIdAscending();
        // IDs are assigned sequentially starting from 100
        List<Integer> expectedIds = testStudents.stream()
                .map(Student::getStudentID)
                .sorted()
                .collect(Collectors.toList());
        List<Integer> actualIds = studentList.getStudentList().stream()
                .map(Student::getStudentID)
                .collect(Collectors.toList());
        assertEquals(expectedIds, actualIds, "Students should be sorted by ID in ascending order.");
    }

    @Test
    void testSortByIdDescending() {
        studentList.sortByIdDescending();
        List<Integer> expectedIds = testStudents.stream()
                .map(Student::getStudentID)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        List<Integer> actualIds = studentList.getStudentList().stream()
                .map(Student::getStudentID)
                .collect(Collectors.toList());
        assertEquals(expectedIds, actualIds, "Students should be sorted by ID in descending order.");
    }
}
