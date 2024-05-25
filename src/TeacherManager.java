import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TeacherManager {
    private HashMap<String, Teacher> teachers = new HashMap<>();
    private static final String TEACHER_FILE = "teachers.dat";

    public TeacherManager() {
        loadTeachers();
    }

    public void addTeacher(String name, String department) {
        Teacher teacher = new Teacher(name, department);
        String id = teacher.getId();
        if (teachers.containsKey(id)) {
            System.out.println("Öğretim görevlisi zaten mevcut.");
        } else {
            teachers.put(id, teacher);
            System.out.println("Öğretim görevlisi başarıyla eklendi.");
            saveTeachers();
        }
    }

    public void deleteTeacher(String id) {
        if (teachers.containsKey(id)) {
            teachers.remove(id);
            System.out.println("Öğretim görevlisi başarıyla silindi.");
            saveTeachers();
        } else {
            System.out.println("Öğretim görevlisi mevcut değil.");
        }
    }

    public void searchTeacher(String id) {
        if (teachers.containsKey(id)) {
            Teacher teacher = teachers.get(id);
            System.out.println("Öğretim Görevlisi Adı: " + teacher.getName());
            System.out.println("Bölüm: " + teacher.getDepartment());
        } else {
            System.out.println("Öğretim görevlisi bulunamadı.");
        }
    }

    public void listTeachers() {
        if (teachers.isEmpty()) {
            System.out.println("Hiç öğretim görevlisi bulunamadı.");
        } else {
            System.out.printf("%-15s %-20s %-20s\n", "OGRETMEN-ID", "OGRETMEN-ADI", "BOLUM-ADI");
            for (Teacher teacher : teachers.values()) {
                System.out.printf("%-15s %-20s %-20s\n",
                        teacher.getId(),
                        teacher.getName(),
                        teacher.getDepartment());
            }
        }
    }

    public void saveTeachers() {
        try {
            FileOutputStream fos = new FileOutputStream(TEACHER_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(teachers);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadTeachers() {
        try {
            FileInputStream fis = new FileInputStream(TEACHER_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            teachers = (HashMap<String, Teacher>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            System.out.println("Öğretim görevlisi kaydı bulunamadı.");
        } catch (ClassNotFoundException c) {
            System.out.println("Class bulunamadı");
            c.printStackTrace();
        }
    }

    public ArrayList<Teacher> getTeachers() {
        return new ArrayList<>(teachers.values());
    }

    public String getTeacherNameById(String teacherId) {
        for (Teacher teacher : teachers.values()) {
            if (teacher.getId().equals(teacherId)) {
                return teacher.getName();
            }
        }
        return "Unknown";
    }
}