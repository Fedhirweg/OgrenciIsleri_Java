import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

    public class StudentManager {
        private HashMap<String, Student> students = new HashMap<>();
        private static final String STUDENT_FILE = "students.dat";

        public StudentManager() {
            loadStudents();
        }
    public void addStudent(String studentName, String ogrenciNo, DepartmentManager departmentManager, Scanner scanner) {
        if (students.containsKey(ogrenciNo)) {
            System.out.println("Öğrenci zaten mevcut.");
        } else {
            departmentManager.listDepartments();
            System.out.println("Öğrencinin bölümünü seçiniz:");
            String departmentName = scanner.nextLine();
            if (departmentManager.departmentExists(departmentName)) {
                students.put(ogrenciNo, new Student(studentName, departmentName, ogrenciNo));
                System.out.println("Öğrenci başarıyla eklendi.");
            } else {
                System.out.println("Bölüm bulunamadı.");
            }
        }
        saveStudents();
    }

    public void deleteStudent(String ogrenciNo) {
        if (students.containsKey(ogrenciNo)) {
            students.remove(ogrenciNo);
            System.out.println("Öğrenci başarıyla silindi.");
        } else {
            System.out.println("Öğrenci mevcut değil.");
        }
        saveStudents();
    }

    public void searchStudent(String ogrenciNo) {
        if (students.containsKey(ogrenciNo)) {
            Student student = students.get(ogrenciNo);
            System.out.println("Öğrenci Adı: " + student.getName());
            System.out.println("Bölüm: " + student.getDepartment());
        } else {
            System.out.println("Öğrenci bulunamadı.");
        }
    }

    public void listStudents(Scanner scanner) {
        System.out.println("Hangi bölümün öğrencilerini listelemek istersiniz?");
        String departmentName = scanner.nextLine();

        ArrayList<Student> departmentStudents = new ArrayList<>();
        for (Student student : students.values()) {
            if (student.getDepartment().equals(departmentName)) {
                departmentStudents.add(student);
            }
        }

        if (departmentStudents.isEmpty()) {
            System.out.println(departmentName + " bölümünde hiç öğrenci bulunamadı.");
        } else {
            System.out.println(departmentName + " bölümündeki öğrenciler:");
            for (Student student : departmentStudents) {
                System.out.println("Öğrenci Adı: " + student.getName());
                System.out.println("Öğrenci Numarası: " + student.getStudentId());
            }
        }
    }

    public void generateStudentDocument(String ogrenciNo) {
            if (students.containsKey(ogrenciNo)) {
                Student student = students.get(ogrenciNo);
                System.out.println(ogrenciNo + " numaralı öğrenci " + (student.isGraduated() ? "MEZUN" : "AKTİF") + " öğrenci durumundadır. Bilgileri aşağıdaki gibidir:");
                System.out.println("Öğrenci Adı: " + student.getName());
                System.out.println("Bölüm: " + student.getDepartment());
                // daha fazla bilgi listeleme eklenecek
            } else {
                System.out.println(ogrenciNo + " numaralı öğrenci bulunamadı.");
            }
        }

        public void graduateStudent(String ogrenciNo) {
        if (students.containsKey(ogrenciNo)) {
            Student student = students.get(ogrenciNo);
            student.setGraduated(true);
            System.out.println(student.getStudentId() + " numaralı öğrenci mezun olarak kaydedildi.");
        } else {
            System.out.println(ogrenciNo + " numaralı öğrenci bulunamadı.");
        }
    }

        public void saveStudents() {
            try {
                FileOutputStream fos = new FileOutputStream(STUDENT_FILE);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(students);
                oos.close();
                fos.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        @SuppressWarnings("unchecked")
        public void loadStudents() {
            try {
                FileInputStream fis = new FileInputStream(STUDENT_FILE);
                ObjectInputStream ois = new ObjectInputStream(fis);
                students = (HashMap<String, Student>) ois.readObject();
                ois.close();
                fis.close();
            } catch (IOException ioe) {
                System.out.println("Öğrenci kaydı bulunamadı.");
            } catch (ClassNotFoundException c) {
                System.out.println("Class bulunamadı");
                c.printStackTrace();
            }
        }
}