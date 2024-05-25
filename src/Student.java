import java.io.Serializable;

public class Student implements Serializable {
    public static int idCounter = 0;
    private final String name;
    private final String department;
    private boolean graduated;
    private final String studentId;

    public Student(String name, String department) {
        this.name = name;
        this.department = department;
        this.graduated = false;
        this.studentId = "S" + idCounter++;
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