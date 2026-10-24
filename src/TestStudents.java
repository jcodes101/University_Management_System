
public class TestStudents {

    public static void main(String[] args) {

        UndergraduateStudent undergraduate = new UndergraduateStudent("Alex", "Johnson", "1001", "3365550101", "Greensboro, NC");

        MSStudent masters = new MSStudent("Jordan", "Smith", "1002", "3365550102", "Charlotte, NC");

        PhDStudent phd = new PhDStudent("Taylor", "Brown", "1003", "3365550103", "Raleigh, NC");

        Course programming = new Course("COMP360", "Programming Languages", 3, "N/A");

        undergraduate.registerCourse(programming);
        masters.registerMSProject("Machine Learning Research");
        phd.registerMSProject("Advanced Computing Research");
        phd.registerDissertation("Artificial Intelligence in Education");

        System.out.println("========== UNDERGRADUATE ==========");
        System.out.println(undergraduate.getStudentInfo());
        System.out.println("Registered Courses: " + undergraduate.getRegisteredCourses().size());

        System.out.println("\n========== MS STUDENT ==========");
        System.out.println(masters.getStudentInfo());

        System.out.println("\n========== PHD STUDENT ==========");
        System.out.println(phd.getStudentInfo());
    }
}
