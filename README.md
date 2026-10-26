# General University Management System

**COMP 360 - Programming Languages | Project 2**

## About the Project

For this project, I created a General University Management System using Java and Java Swing. The main goal was to build a program that uses object-oriented programming concepts, especially single inheritance, while also giving users a graphical interface to interact with the system.

The program allows users to add students, register them for courses, view their information, assign course grades, and calculate their GPA. It also supports three different types of students: Undergraduate, MS, and PhD.

Each student type has different registration options depending on their academic level. Undergraduate students can register for courses, MS students can register for courses and an MS project, and PhD students can register for courses, an MS project, and a PhD dissertation.

Instead of having users enter information through the terminal, I used Java Swing to create a desktop application with different tabs, buttons, and input fields.

## How the Program Works

When the program starts, the `Main` class opens the graphical interface created in `UniversityGUI`.

The interface has different tabs that allow users to interact with the university management system.

### Add Student

Users can add a new student by entering their first name, last name, student ID, phone number, and address. They also select whether the student is an Undergraduate, MS, or PhD student.

Once the information is submitted, the program creates an object of the selected student type and adds it to the system.

The program also checks student IDs to prevent duplicate student records.

### Register Course

Users can enter a student's ID and select a course from the available course list.

The program searches for the student using their ID and registers them for the selected course.

After registration, the program displays the student's information along with their registered courses.

It also prevents students from registering for the same course more than once.

### Available Courses

This tab displays the courses available for registration, including their course codes, names, and credit hours.

The available courses are stored in the `UniversityManager` class.

### Student Records

Users can search for a student by entering their student ID.

The program displays their name, ID, phone number, address, student type, registered courses, course grades, and GPA.

For MS and PhD students, their registered research projects or dissertations are also displayed.

### Graduate Research

This tab allows eligible graduate students to register for an MS project or PhD dissertation.

MS students can register for an MS project, while PhD students can register for both an MS project and a PhD dissertation.

Undergraduate students cannot register for either option.

The program checks the student's type before allowing the registration.

### Grade Management

Users can enter a student ID, load the courses that student is registered for, and assign a letter grade.

The program supports grades A, B, C, D, and F.

After a grade is assigned, the student's record is updated, and their GPA is calculated using the grades and credit hours of their graded courses.

Courses that have not received a grade are not included in the GPA calculation.

## What Each Class Does

### Student.java

This is the parent class of the program.

It stores the information that all students have in common, including their first name, last name, student ID, phone number, address, and registered courses.

It also contains methods for retrieving student information, registering courses, and displaying student details.

Instead of writing these same variables and methods in every student class, the other student classes inherit them from `Student`.

### UndergraduateStudent.java

This class extends the `Student` class.

It represents undergraduate students and uses the course registration methods inherited from the parent class.

It also overrides `getStudentType()` so the program can identify the student as an undergraduate.

### MSStudent.java

This class also extends `Student`.

It inherits the basic student information and course registration methods, but adds functionality for registering an MS project.

It also overrides methods to display the student's type and include their MS project in their information.

### PhDStudent.java

This class extends `Student` and represents PhD students.

Along with the inherited student information and course registration methods, it includes functionality for registering an MS project and a PhD dissertation.

It also overrides methods to display the student's type and research information.

### Course.java

This class represents a university course.

It stores the course code, course name, credit hours, and grade.

It also contains methods for retrieving course information, updating grades, and converting letter grades into grade points for GPA calculations.

### UniversityManager.java

This class handles the main operations of the university management system.

It stores the students and available courses using ArrayLists.

It contains methods for adding students, finding students by ID, adding courses, finding available courses, registering students for courses, assigning grades, calculating GPA, and displaying student records.

It also checks for duplicate student IDs and course registrations.

### UniversityGUI.java

This class creates the graphical user interface using Java Swing.

It contains the different tabs, buttons, text fields, dropdown menus, and display areas that users interact with.

When a user clicks a button, the GUI calls the appropriate methods from `UniversityManager` or the student classes to perform the requested operation.

The GUI displays the results so users do not have to interact with the program through the terminal.

### Main.java

This is the starting point of the program.

It contains the `main()` method, which launches the `UniversityGUI` window.

## How the Classes Work Together

The program is separated into different classes so that each class has its own responsibility.

For example, when a user adds a new student:

1. The user enters the student's information in `UniversityGUI`.
2. The GUI creates an `UndergraduateStudent`, `MSStudent`, or `PhDStudent` object depending on the selected student type.
3. The student object inherits its basic information and methods from `Student`.
4. `UniversityManager` checks whether the student ID already exists.
5. If the ID is available, the student is added to the system.
6. The GUI displays a message letting the user know whether the student was added successfully.

Course registration works in a similar way.

The GUI sends the student ID and selected course code to `UniversityManager`. The manager finds the student and course, then uses the student's inherited `registerCourse()` method to register the course.

Each student receives their own course record so that assigning a grade to one student does not affect another student's grade.

When a user assigns a grade, the manager finds the student's registered course and updates its grade. The program then uses the course's grade points and credit hours to calculate the student's GPA.

This way, the GUI handles user interaction, the manager handles the main operations, and the student and course classes store the information.

## Object-Oriented Programming Concepts Used

### Inheritance

The main concept demonstrated in this project is single inheritance.

All three student types inherit directly from the `Student` parent class using the `extends` keyword.

This allows the student classes to reuse common information and methods without rewriting the same code multiple times.

### Encapsulation

The program uses private variables to store student and course information.

Getter methods are used to retrieve information, while other methods control how information is updated.

This helps keep the data organized and prevents other classes from directly accessing private variables.

### Polymorphism

The student subclasses override methods such as `getStudentType()` and `getStudentInfo()`.

Even though the classes share the same parent, each student type can return different information depending on the object being used.

The `UniversityManager` can also store all three student types as `Student` objects.

### Abstraction

The program separates the graphical interface from the main management operations.

Users interact with buttons and forms without needing to know how the underlying Java methods work.

The different classes also separate responsibilities so the program is easier to understand and maintain.

## Project Structure

```text
university-management-system/
├── src/
│   ├── Student.java
│   ├── UndergraduateStudent.java
│   ├── MSStudent.java
│   ├── PhDStudent.java
│   ├── Course.java
│   ├── UniversityManager.java
│   ├── UniversityGUI.java
│   └── Main.java
├── tests/
│   ├── TestStudents.java
│   └── TestUniversityManager.java
├── screenshots/
├── README.md
└── .gitignore
```

## How to Run the Program

The project was developed using Java 27.

Open a terminal in the main project folder.

**1. Compile the Java files:**

```powershell
javac -d out src/*.java tests/*.java
```

**2. Run the application:**

```powershell
java -cp out Main
```

A window titled **General University Management System** will open.

From there, users can add students, register courses, view student records, assign grades, and register graduate research.

## Testing

I created two test files to check the functionality of the program before connecting everything to the graphical interface.

**TestStudents.java** checks the different student types, inheritance, course registration, MS project registration, and PhD dissertation registration.

**TestUniversityManager.java** checks adding students, adding available courses, course registration, duplicate registration prevention, grade assignment, and GPA calculations.

To run the tests:

```powershell
java -cp out TestStudents
java -cp out TestUniversityManager
```

## Additional Notes

The program stores student and course information in memory while it is running. This means any students or registrations added during a session will reset when the application is closed.

The main focus of this project was demonstrating object-oriented programming concepts, especially single inheritance, while building a working graphical interface in Java Swing.
