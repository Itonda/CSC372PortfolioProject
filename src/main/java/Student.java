
// This class is the blueprint for a student object
public class Student {
    // Static variable to track the next student ID
    private static int nextStudentID = 100;
    // Attributes
    private int studentID;
    private String name;
    private String address;
    private Double gpa;  
    // Default constructor
    public Student() {
        this.studentID = nextStudentID++;
        this.name = "null";
        this.address = "null";
        this.gpa = 0.0;
    }
    // Parameterized contructor
    public Student(String name, String address, Double gpa) {
        this.studentID = nextStudentID++;
        this.name = name;
        this.address = address;
        this.gpa = gpa;
    }
    // Getter methods
    public int getStudentID() {
        return studentID;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public Double getGPA() {
        return gpa;
    }
    // toString method 
    @Override
    public String toString() {
        return "[Student ID: " + studentID + 
                " - Student Name: " + name +
                " - Student Address: " + address +
                " - Student GPA: " + gpa + "]";
    }  
    /* FOR TESTING PURPOSES ONLY - COMMENT OUT IN PRODUCTION
     * This method resets the next student ID to the base value
     * Helps consistent testing */
    public static void resetNextStudentID() {
      nextStudentID = 100; // 100 is the base value
    } 
}