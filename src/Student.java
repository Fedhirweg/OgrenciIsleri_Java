import java.io.Serializable;

public class Student implements Serializable {
    private final String name;
    private final String department;
    private boolean graduated;
    private final String studentId;

    public Student(String name, String department, String studentId) {
        this.name = name;
        this.department = department;
        this.graduated = false;
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public boolean isGraduated() {
        return graduated;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setGraduated(boolean graduated) {
        this.graduated = graduated;
    }
}