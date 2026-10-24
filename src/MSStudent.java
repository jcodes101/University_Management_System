public class MSStudent extends Student {

    // this stores the MS project assigned to this student
    private String msProject;

    // constructor creates MS student
    public MSStudent(String firstName, String lastName, String studentId, String phoneNumber, String address) {

        super(firstName, lastName, studentId, phoneNumber, address);

        // this is for a project that is not registered
        this.msProject = "Not Registered";
    }

    // registers an MS project if one has not alr been registered
    public boolean registerMSProject(String projectName) {

        if (projectName == null || projectName.isBlank()) {
            return false;
        }

        if (!msProject.equals("Not Registered")) {
            return false;
        }

        msProject = projectName.trim();
        return true;
    }

    // returns the student's MS project
    public String getMSProject() {
        return msProject;
    }

    @Override
    public String getStudentType() {
        return "MS Student";
    }

    // adds MS project information to the parent class summary.
    @Override
    public String getStudentInfo() {
        return super.getStudentInfo() + "\nMS Project: " + msProject;
    }

}