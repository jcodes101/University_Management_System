
import javax.swing.*;
import java.awt.*;

public class UniversityGUI extends JFrame {

    private UniversityManager manager;
    private JTextArea outputArea;

    // constructor for setting up the university management window
    public UniversityGUI() {
        manager = new UniversityManager();

        setTitle("General University Management System");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // adds sample courses to the university catalog
        manager.addCourse(new Course("COMP360", "Programming Languages", 3, "No Grade"));
        manager.addCourse(new Course("MATH341", "Differential Equations", 3, "No Grade"));
        manager.addCourse(new Course("COMP267", "Database Design", 3, "No Grade"));
        manager.addCourse(new Course("COMP350", "Operating Systems", 3, "No Grade"));

        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Add Student", createStudentPanel());
        tabs.addTab("Register Course", createRegistrationPanel());
        tabs.addTab("Available Courses", createCoursesPanel());
        tabs.addTab("Student Records", createRecordsPanel());
        tabs.addTab("Graduate Research", createResearchPanel());

        add(tabs);
    }

    // creates the form for adding students
    private JPanel createStudentPanel() {
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JTextField firstName = new JTextField();
        JTextField lastName = new JTextField();
        JTextField studentId = new JTextField();
        JTextField phone = new JTextField();
        JTextField address = new JTextField();

        JComboBox<String> studentType = new JComboBox<>(
                new String[]{"Undergraduate", "MS", "PhD"}
        );

        panel.add(new JLabel("First Name:"));
        panel.add(firstName);
        panel.add(new JLabel("Last Name:"));
        panel.add(lastName);
        panel.add(new JLabel("Student ID:"));
        panel.add(studentId);
        panel.add(new JLabel("Phone Number:"));
        panel.add(phone);
        panel.add(new JLabel("Address:"));
        panel.add(address);
        panel.add(new JLabel("Student Type:"));
        panel.add(studentType);

        JButton addButton = new JButton("Add Student");

        addButton.addActionListener(e -> {
            String first = firstName.getText().trim();
            String last = lastName.getText().trim();
            String id = studentId.getText().trim();
            String phoneNumber = phone.getText().trim();
            String studentAddress = address.getText().trim();

            if (first.isEmpty() || last.isEmpty() || id.isEmpty()
                    || phoneNumber.isEmpty() || studentAddress.isEmpty()) {
                showMessage("Please complete all student fields.");
                return;
            }

            String type = (String) studentType.getSelectedItem();
            Student student;

            if ("MS".equals(type)) {
                student = new MSStudent(first, last, id, phoneNumber, studentAddress);
            } else if ("PhD".equals(type)) {
                student = new PhDStudent(first, last, id, phoneNumber, studentAddress);
            } else {
                student = new UndergraduateStudent(first, last, id, phoneNumber, studentAddress);
            }

            if (manager.addStudent(student)) {
                showMessage("Student added successfully!");
                firstName.setText("");
                lastName.setText("");
                studentId.setText("");
                phone.setText("");
                address.setText("");
            } else {
                showMessage("A student with that ID already exists.");
            }
        });

        panel.add(new JLabel(""));
        panel.add(addButton);

        return panel;
    }

    // creates the course registration form
    private JPanel createRegistrationPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));

        JTextField studentId = new JTextField();
        JComboBox<String> courseBox = new JComboBox<>();

        for (Course course : manager.getAvailableCourses()) {
            courseBox.addItem(course.getCourseCode());
        }

        form.add(new JLabel("Student ID:"));
        form.add(studentId);
        form.add(new JLabel("Select Course:"));
        form.add(courseBox);

        JButton registerButton = new JButton("Register Course");
        form.add(new JLabel(""));
        form.add(registerButton);

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);

        registerButton.addActionListener(e -> {
            String id = studentId.getText().trim();
            String courseCode = (String) courseBox.getSelectedItem();

            if (id.isEmpty() || courseCode == null) {
                showMessage("Enter a student ID and select a course.");
                return;
            }

            if (manager.registerStudentForCourse(id, courseCode)) {
                resultArea.setText(manager.getStudentRecord(id));
            } else {
                showMessage("Registration failed. Check the ID or duplicate course.");
            }
        });

        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        return panel;
    }

    // displays all available courses
    private JPanel createCoursesPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JTextArea courseArea = new JTextArea();
        courseArea.setEditable(false);

        JButton refreshButton = new JButton("View Available Courses");

        refreshButton.addActionListener(e -> {
            StringBuilder text = new StringBuilder("AVAILABLE COURSES\n\n");

            for (Course course : manager.getAvailableCourses()) {
                text.append(course.getCourseCode())
                        .append(" - ")
                        .append(course.getCourseName())
                        .append(" (")
                        .append(course.getCreditHours())
                        .append(" credits)\n");
            }

            courseArea.setText(text.toString());
        });

        panel.add(refreshButton, BorderLayout.NORTH);
        panel.add(new JScrollPane(courseArea), BorderLayout.CENTER);

        return panel;
    }

    // allows users to search for a student and view their records
    private JPanel createRecordsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JPanel searchPanel = new JPanel(new BorderLayout(10, 10));
        JTextField studentId = new JTextField();
        JButton searchButton = new JButton("View Student Record");

        searchPanel.add(new JLabel("Student ID:"), BorderLayout.WEST);
        searchPanel.add(studentId, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        JTextArea recordsArea = new JTextArea();
        recordsArea.setEditable(false);

        searchButton.addActionListener(e -> {
            recordsArea.setText(manager.getStudentRecord(studentId.getText().trim()));
        });

        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(recordsArea), BorderLayout.CENTER);

        return panel;
    }

    // this handles MS project and PhD dissertation registration
    private JPanel createResearchPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));

        JTextField studentId = new JTextField();
        JTextField researchTitle = new JTextField();

        JComboBox<String> researchType = new JComboBox<>(
                new String[]{"MS Project", "PhD Dissertation"}
        );

        form.add(new JLabel("Student ID:"));
        form.add(studentId);
        form.add(new JLabel("Research Title:"));
        form.add(researchTitle);
        form.add(new JLabel("Research Type:"));
        form.add(researchType);

        JButton registerButton = new JButton("Register Research");

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);

        registerButton.addActionListener(e -> {
            Student student = manager.findStudent(studentId.getText().trim());
            String title = researchTitle.getText().trim();

            if (student == null || title.isEmpty()) {
                showMessage("Enter a valid student ID and research title.");
                return;
            }

            String type = (String) researchType.getSelectedItem();
            boolean success = false;

            if ("MS Project".equals(type)) {
                if (student instanceof MSStudent) {
                    success = ((MSStudent) student).registerMSProject(title);
                } else if (student instanceof PhDStudent) {
                    success = ((PhDStudent) student).registerMSProject(title);
                }
            } else if ("PhD Dissertation".equals(type)) {
                if (student instanceof PhDStudent) {
                    success = ((PhDStudent) student).registerDissertation(title);
                }
            }

            if (success) {
                resultArea.setText(manager.getStudentRecord(student.getStudentId()));
            } else {
                showMessage("Registration failed. Check student eligibility or existing registration.");
            }
        });

        panel.add(form, BorderLayout.NORTH);
        panel.add(registerButton, BorderLayout.CENTER);
        panel.add(new JScrollPane(resultArea), BorderLayout.SOUTH);

        return panel;
    }

    // displays a popup message to the user.
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
