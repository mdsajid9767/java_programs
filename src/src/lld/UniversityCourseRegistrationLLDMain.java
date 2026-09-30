package lld;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Course {
    private String name;
    private String code;
    private int creditHours;

    public Course(String name, String code, int creditHours) {
        this.name = name;
        this.code = code;
        this.creditHours = creditHours;
    }

    public String getCode() {
        return code;
    }

    public String getDetails() {
        return "Course Name: " + name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", creditHours=" + creditHours +
                '}';
    }
}

class Student {
    private String name;
    private String email;
    List<Course> courses = new ArrayList<>();

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void enrollCourse(Course course) {
        this.courses.add(course);
    }

    public void removeCourse(String code) {
        Iterator<Course> iterator = courses.iterator();

        while (iterator.hasNext()) {
            Course course = iterator.next();

            if (course.getCode().equals(code)) {
                iterator.remove();
                System.out.println("Course removed: " + code);
                return;
            }
        }

        System.out.println("Course not found: " + code);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", courses=" + courses +
                '}';
    }
}

class Professor {
    private String name;
    private String email;
    List<Course> courses = new ArrayList<>();

    public Professor(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void teachCourse(Course course) {
        this.courses.add(course);
    }

    public void removeCourse(String code) {
        Iterator<Course> iterator = courses.iterator();

        while (iterator.hasNext()) {
            Course course = iterator.next();

            if (course.getCode().equals(code)) {
                iterator.remove();
                System.out.println("Course removed: " + code);
                return;
            }
        }

        System.out.println("Course not found: " + code);
    }

    @Override
    public String toString() {
        return "Professor{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", courses=" + courses +
                '}';
    }
}

class Department {
    private static final String departmentName = "BCA";
    List<Course> courseList = new ArrayList<>();
    List<Professor> professors = new ArrayList<>();
    List<Student> students = new ArrayList<>();

    public Department() {
        courseList.add(new Course("Java Development", "JAVA", 72));
        courseList.add(new Course("Python Development", "PYTHON", 48));
        courseList.add(new Course("AI/ML", "AIML", 72));
    }

    public void addProfessor(Professor professor) {
        this.professors.add(professor);
    }

    public void teachCourse(Professor professor, String code) {
        for (Course course : courseList) {
            if (course.getCode().equals(code)) {
                professor.teachCourse(course);
                return;
            }
        }
        System.out.println("Course not found: " + code);
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void enrollCourse(Student student, String code) {
        for (Course course : courseList) {
            if (course.getCode().equals(code)) {
                student.enrollCourse(course);
                return;
            }
        }
        System.out.println("Course not found: " + code);
    }

    public static String getDepartmentName() {
        return departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "courseList=" + courseList +
                ", professors=" + professors +
                ", students=" + students +
                '}';
    }
}

public class UniversityCourseRegistrationLLDMain {
    public static void main(String[] args) {

        Department dept = new Department();

        Student s1 = new Student("Sajid", "sajid@gmail.com");
        dept.addStudent(s1);
        dept.enrollCourse(s1, "JAVA");

        Professor p1 = new Professor("Arif", "arif@gmail.com");
        dept.addProfessor(p1);
        dept.teachCourse(p1, "PYTHON");

    }
}
