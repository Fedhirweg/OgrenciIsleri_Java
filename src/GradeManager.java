import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class GradeManager {
    private HashMap<String, Grade> grades = new HashMap<>();
    private static final String GRADE_FILE = "grades.dat";

    public GradeManager() {
        loadGrades();
    }

    public void addGrade(Grade grade) {
        if (grades.containsKey(grade.getGradeId())){
            System.out.println("Not zaten mevcut.");
        } else {
            grades.put(grade.getGradeId(), grade);
            System.out.println("Not başarıyla eklendi.");
            saveGrades();
        }
    }

    public void listGrades() {
        if (grades.isEmpty()) {
            System.out.println("Hiç not bulunamadı.");
        } else {
            // Sort the grades by student name
            List<Grade> sortedGrades = grades.values().stream()
                    .sorted(Comparator.comparing(Grade::getStudentName))
                    .collect(Collectors.toList());

            System.out.printf("%-10s %-20s %-20s %-20s %-10s %-20s\n", "NOT-ID", "DERS-ADI", "OGRENCI-NO", "OGRENCI-ADI", "PUAN", "BOLUM-ADI");
            for (Grade grade : sortedGrades) {
                System.out.printf("%-10s %-20s %-20s %-20s %-10d %-20s\n",
                        grade.getGradeId(),
                        grade.getCourseName(),
                        grade.getStudentId(),
                        grade.getStudentName(),
                        grade.getScore(),
                        grade.getDepartmentName());
            }
        }
    }
    public void deleteGrade(String gradeId) {
        if (grades.containsKey(gradeId)) {
            grades.remove(gradeId);
            System.out.println("Not başarıyla silindi.");
            saveGrades();
        } else {
            System.out.println("Not mevcut değil.");
        }
    }

    public void saveGrades() {
        try {
            FileOutputStream fos = new FileOutputStream(GRADE_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(grades);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadGrades() {
        try {
            FileInputStream fis = new FileInputStream(GRADE_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            grades = (HashMap<String, Grade>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            System.out.println("Not kaydı bulunamadı.");
        } catch (ClassNotFoundException c) {
            System.out.println("Class bulunamadı");
            c.printStackTrace();
        }
    }
}