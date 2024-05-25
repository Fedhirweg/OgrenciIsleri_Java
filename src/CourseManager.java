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
            System.out.println("Course already exists.");
        } else {
            courses.put(course.getCourseId(), course);
            System.out.println("Course added successfully.");
            saveCourses();
        }
    }

    public void deleteCourse(String courseId) {
        if (courses.containsKey(courseId)) {
            courses.remove(courseId);
            System.out.println("Course deleted successfully.");
            saveCourses();
        } else {
            System.out.println("Course does not exist.");
        }
    }

    public void updateCourse(Course course) {
        if (courses.containsKey(course.getCourseId())) {
            courses.put(course.getCourseId(), course);
            System.out.println("Course updated successfully.");
            saveCourses();
        } else {
            System.out.println("Course does not exist.");
        }
    }

    public void listCourses() {
        for (Course course : courses.values()) {
            System.out.println(course.getCourseName());
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
