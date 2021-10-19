import java.util.ArrayList;

public class School {
    ArrayList<Teacher> teachers = new ArrayList<>();
    ArrayList<Student> students = new ArrayList<>();

    // add a new teacher to teacher list
    void addTeacher (String firstName, String lastName, String subject) {
        Teacher teacher = new Teacher(firstName, lastName, subject);
        teachers.add(teacher);
    }

    // remove the teacher who matches the given name from teacher list
    void delTeacher (String firstName, String lastName, String subject) {
        // find teacher
        for (Teacher teacher : teachers) {
            if (teacher.getFirstName().equals(firstName) &&
                    teacher.getLastName().equals(lastName) &&
                    teacher.getSubject().equals(subject)) {
                // remove the teacher from list
                teachers.remove(teacher);
                System.out.println("Teacher: " + firstName + " " + lastName + " has been deleted");
                return;
            }
        }

        System.out.println("Student: " + firstName + " " + lastName + " does not exist");
    }

    // add a new student to student list
    void addStudent (String firstName, String lastName, int grade) {
        Student student = new Student(firstName, lastName, grade);
        students.add(student);
    }

    // remove the teacher who matches the given name from teacher list
    void delStudent (String firstName, String lastName, int grade) {
        // find student
        for (Student student : students) {
            if (student.getFirstName().equals(firstName) &&
                    student.getLastName().equals(lastName) &&
                    student.getGrade() == grade) {
                // remove the student from list
                students.remove(student);
                System.out.println("Student: " + firstName + " " + lastName + " has been deleted");
                return;
            }
        }

        System.out.println("Student: " + firstName + " " + lastName + " does not exist");
    }

    // display all students in list
    void displayAllStudents () {
        for (Student student : students)
            System.out.println(student);
    }

    // display all teachers in list
    void displayAllTeachers () {
        for (Teacher teacher : teachers)
            System.out.println(teacher);
    }
}
