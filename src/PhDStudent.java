
public class PhDStudent extends Student {

    // this stores the student's registered MS project and dissertation
    private String msProject;
    private String dissertation;

    // Constructor: creates a PhD student.
    public PhDStudent(String firstName, String lastName, String studentId, String phoneNumber, String address) {

        super(firstName, lastName, studentId, phoneNumber, address);

        this.msProject = "Not Registered";
        this.dissertation = "Not Registered";
    }

    // Registers an MS project.
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

    // this registers a PhD dissertation
    public boolean registerDissertation(String dissertationTitle) {

        if (dissertationTitle == null || dissertationTitle.isBlank()) {
            return false;
        }

        if (!dissertation.equals("Not Registered")) {
            return false;
        }

        dissertation = dissertationTitle.trim();
        return true;
    }

    public String getMSProject() {
        return msProject;
    }

    public String getDissertation() {
        return dissertation;
    }

    @Override
    public String getStudentType() {
        return "PhD Student";
    }

    // both project and dissertation information
    @Override
    public String getStudentInfo() {
        return super.getStudentInfo() + "\nMS Project: " + msProject + "\nPhD Dissertation: " + dissertation;
    }

}
