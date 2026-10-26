
public class TestUniversityManager {

    public static void main(String[] args) {

        UniversityManager manager = new UniversityManager();

        Student student = new UndergraduateStudent(
                "Alex", "Johnson", "1001",
                "3365550101", "Greensboro, NC"
        );

        manager.addStudent(student);

        manager.addCourse(new Course(
                "COMP360", "Programming Languages", 3, "N/A"
        ));

        manager.addCourse(new Course(
                "MATH341", "Differential Equations", 3, "N/A"
        ));

        System.out.println("Students: " + manager.getStudents().size());
        System.out.println("Available Courses: "
                + manager.getAvailableCourses().size());

        boolean registered = manager.registerStudentForCourse(
                "1001", "COMP360"
        );

        System.out.println("Registration successful: " + registered);

        System.out.println("\n========== STUDENT RECORD ==========");
        System.out.println(manager.getStudentRecord("1001"));

        // test duplicate registration.
        boolean duplicate = manager.registerStudentForCourse(
                "1001", "COMP360"
        );

        System.out.println("\nDuplicate registration accepted: " + duplicate);
    }
}
