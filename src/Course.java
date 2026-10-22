public class Course {

    // basic info about the course
    private String courseCode;
    private String courseName;
    private int creditHours;
    private String grade;

    // constructor for creating a course
    public Course(String courseCode, String courseName, int creditHours, String grade) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.grade = "No Grade";
    }

    // getters & setters
    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    // maps letter grade into points
    public double getGradePoints() {
        
        switch(grade.toUpperCase()) {
            case "A":
                return 4.0;
            case "B":
                return 3.0;
            case "C":
                return 2.0;
            case "D":
                return 1.0;
            case "F":
                return 0.0;
            default:
                return -1.0;
        }
    }

    // prints out readable course description
    public String getCourseInfo() {

        return courseCode + " - " + courseName
                + " (" + creditHours + " c redits)"
                + " | Grade: " + grade;
    }
}