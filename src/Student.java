import java.util.ArrayList;
import java.util.List;

public class Student {

    // this is basic info share by every student
    private String firstName;
    private String lastName;
    private String studentId;
    private String phoneNumber;
    private String address;

    // a list that stores all courses a student is registered for
    private List<Course> registeredCourses;

    // student constructor
    public Student (String firstName, String lastName, String studentId, String phoneNumber, String address) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
        this.phoneNumber = phoneNumber;
        this.address = address;

        registeredCourses = new ArrayList<>();
    }

    // getters for other classes reading student info
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public List<Course> getRegisteredCourses() {
        return new ArrayList<>(registeredCourses);
    }

    // this registers a course for a student if they aren't in it already
    public boolean registerCourse(Course course) {

        if (course = null) {
            return false;
        }

        for (Course registeredCourses : registeredCourses) {
            if (registeredCourses.getCourseCode().equals(course.getCourseCode())) {
                return false:
            }
        }

        registeredCourses.add(course);
        return true;
    }

    // returns the type of student
    public String getStudentType() {
        return "Student";
    }

    // print a summary of student info
    public String getStudentId() {
        return "Name: " + firstName + " " + lastName
                + "\nStudent ID: " + studentId
                + "\nPhone Number: " + phoneNumber
                + "\nAddress: " + address
                + "\nStudent Type: " + getStudentType();
    }
}