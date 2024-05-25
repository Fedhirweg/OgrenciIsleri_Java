import java.io.*;
import java.util.List;

public class Course implements Serializable {
    private static int idCounter = 1;
    private final String courseId;
    private final String courseName;
    private final String teacherId;
    private final List<String> studentIds;
    private int creditScore;

    // constructor, getters, setters
    public Course(String courseName, String teacherId, List<String> studentIds, int updateCreditScore) {
        this.courseId = "C" + idCounter++;
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

    public static void saveIdCounter() {
        try {
            FileOutputStream fos = new FileOutputStream("courseIdCounter.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeInt(idCounter);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void loadIdCounter() {
        try {
            FileInputStream fis = new FileInputStream("courseIdCounter.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            idCounter = ois.readInt();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            idCounter = 0;
        }
    }
}