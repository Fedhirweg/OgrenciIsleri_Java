import java.io.Serializable;

public class Grade implements Serializable {
    private String gradeId;
    private final String courseName;
    private final String studentId;
    private final String studentName;
    private final int score;
    private final String departmentName;
    private static int gradeCounter = 0;

    public Grade(String courseName, String studentId, String studentName, int score, String departmentName) {
        this.gradeId = "G" + gradeCounter++;
        this.courseName = courseName;
        this.studentId = studentId;
        this.studentName = studentName;
        this.score = score;
        this.departmentName = departmentName;
    }

    // getters
    public String getGradeId() {
        return gradeId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getScore() {
        return score;
    }

    public String getDepartmentName() {
        return departmentName;
    }
}