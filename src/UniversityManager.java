
import java.util.ArrayList;
import java.util.List;

public class UniversityManager {

    // this is what allows storing of all students and available university courses
    private List<Student> students;
    private List<Course> availableCourses;

    // constructor that creates an empty university management system.
    public UniversityManager() {
        students = new ArrayList<>();
        availableCourses = new ArrayList<>();
    }

    // appends a student if their ID is not already registered
    public boolean addStudent(Student student) {
        if (student == null || findStudent(student.getStudentId()) != null) {
            return false;
        }

        students.add(student);
        return true;
    }

    // searches for a student using their ID number
    public Student findStudent(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }

        return null;
    }

    // returns a copy of the student list
    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    // adds a course to the university's available course catalog
    public boolean addCourse(Course course) {
        if (course == null || findCourse(course.getCourseCode()) != null) {
            return false;
        }

        availableCourses.add(course);
        return true;
    }

    // goes ahead and finds an available course by its course code
    public Course findCourse(String courseCode) {
        for (Course course : availableCourses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }

        return null;
    }

    // returns all available university courses
    public List<Course> getAvailableCourses() {
        return new ArrayList<>(availableCourses);
    }

    // this part registers a student for a course using their ID and course code
    public boolean registerStudentForCourse(String studentId, String courseCode) {
        Student student = findStudent(studentId);
        Course course = findCourse(courseCode);

        if (student == null || course == null) {
            return false;
        }

        // this is for each student to get a separate Course object for independent grades
        Course registeredCourse = new Course(
                course.getCourseCode(),
                course.getCourseName(),
                course.getCreditHours(),
                "N/A"
        );

        return student.registerCourse(registeredCourse);
    }

    // calculate a student's GPA using graded courses
    public double calculateGPA(String studentId) {
        Student student = findStudent(studentId);

        if (student == null) {
            return 0.0;
        }

        double totalGradePoints = 0.0;
        int totalCredits = 0;

        for (Course course : student.getRegisteredCourses()) {
            double gradePoints = course.getGradePoints();

            // it ignores courses that have not received a valid grade.
            if (gradePoints >= 0) {
                totalGradePoints += gradePoints * course.getCreditHours();
                totalCredits += course.getCreditHours();
            }
        }

        if (totalCredits == 0) {
            return 0.0;
        }

        return totalGradePoints / totalCredits;
    }

    // this shows a student's basic information and registered courses
    public String getStudentRecord(String studentId) {
        Student student = findStudent(studentId);

        if (student == null) {
            return "Student not found.";
        }

        StringBuilder record = new StringBuilder();
        record.append(student.getStudentInfo());
        record.append("\n\nRegistered Courses:\n");

        if (student.getRegisteredCourses().isEmpty()) {
            record.append("No courses registered.\n");
        } else {
            for (Course course : student.getRegisteredCourses()) {
                record.append(course.getCourseInfo()).append("\n");
            }
        }

        record.append("\nGPA: ");
        record.append(String.format("%.2f", calculateGPA(studentId)));

        return record.toString();
    }
}
