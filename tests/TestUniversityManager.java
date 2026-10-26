
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

        
        // this tests grade assignment and GPA calculation
        manager.registerStudentForCourse("1001", "MATH341");

        boolean grade1 = manager.assignGrade("1001", "COMP360", "A");
        boolean grade2 = manager.assignGrade("1001", "MATH341", "B");

        System.out.println("\n========== GRADE TEST ==========");
        System.out.println("COMP360 grade assigned: " + grade1);
        System.out.println("MATH341 grade assigned: " + grade2);
        System.out.println("Updated GPA: " + manager.calculateGPA("1001"));

        // a check for whether the calculated GPA is correct
        if (Math.abs(manager.calculateGPA("1001") - 3.5) < 0.001) {
            System.out.println("GPA test passed!");
        } else {
            System.out.println("GPA test failed!");
        }

    }
}
