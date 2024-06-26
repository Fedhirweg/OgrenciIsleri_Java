import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CourseManager {
    private HashMap<String, Course> courses = new HashMap<>();
    private static final String COURSE_FILE = "courses.dat";

    public CourseManager() {
        loadCourses();
    }

    public ArrayList<Course> getCourses() {
        return new ArrayList<>(courses.values());
    }

    public void addCourse(Course course) {
        if (courses.containsKey(course.getCourseId())) {
            System.out.println("Ders zaten mevcut.");
        } else {
            courses.put(course.getCourseId(), course);
            System.out.println("Ders başarıyla eklendi");
            saveCourses();
        }
    }

    public void deleteCourse(String courseId) {
        if (courses.containsKey(courseId)) {
            courses.remove(courseId);
            System.out.println("Ders başarıyla silindi");
            saveCourses();
        } else {
            System.out.println("Ders mevcut değil");
        }
    }

    public void listCourses(TeacherManager teacherManager) {
        System.out.printf("%-10s %-20s %-20s %-10s\n", "Ders ID", "Ders Adı", "Öğr. Gör. Adı", "AKTS");
        for (Course course : courses.values()) {
            String teacherName = teacherManager.getTeacherNameById(course.getTeacherId());
            System.out.printf("%-10s %-20s %-20s %-10d\n",
                    course.getCourseId(),
                    course.getCourseName(),
                    teacherName,
                    course.getCreditScore());
        }
    }

    public void saveCourses() {
        try {
            FileOutputStream fos = new FileOutputStream(COURSE_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(courses);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadCourses() {
        try {
            FileInputStream fis = new FileInputStream(COURSE_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            courses = (HashMap<String, Course>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            System.out.println("No course record found.");
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
    }


}
