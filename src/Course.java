import java.io.Serializable;
import java.util.List;

public class Course implements Serializable {
    private final String courseId;
    private final String courseName;
    private final String teacherId;
    private final List<String> studentIds;
    private int creditScore;

    // constructor, getters, setters
    public Course(String courseId, String courseName, String teacherId, List<String> studentIds, int updateCreditScore) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacherId = teacherId;
        this.studentIds = studentIds;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public List<String> getStudentIds() {
        return studentIds;
    }

    public int getCreditScore() {
        return creditScore;
    }
}