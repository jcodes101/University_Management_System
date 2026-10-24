public class UndergraduateStudent extends Student {

    // constructor that creates an undergrad student
    public UndergraduateStudent(String firstName, String lastName, String studentId, String phoneNumber, String address) {

        // calls the constructor in the Student parent class
        super(firstName, lastName, studentId, phoneNumber, address);
    }

    // overrides the parent method to identify this student as a undergrad
    @Override
    public String getStudentType() {
        return "Undergraduate Student";
    }
}