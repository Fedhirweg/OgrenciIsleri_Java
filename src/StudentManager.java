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

        public ArrayList<Student> getStudents() {
            return new ArrayList<>(students.values());
        }

        public void addStudent(String studentName, DepartmentManager departmentManager, Scanner scanner) {
            HashMap<String, String> departmentList = departmentManager.getDepartments();
            ArrayList<String> departmentNames = new ArrayList<>(departmentList.values());
            System.out.println("Öğrencinin TC Kimlik Numarasını giriniz:");
            String tcKimlikNo = scanner.nextLine();
            for (int i = 0; i < departmentNames.size(); i++) {
                System.out.println((i + 1) + ". " + departmentNames.get(i));
            }
            System.out.println("Öğrencinin bölümünü seçiniz:");
            int departmentIndex = scanner.nextInt() - 1;
            scanner.nextLine();  // consume newline
            if (departmentIndex >= 0 && departmentIndex < departmentNames.size()) {
                String departmentName = departmentNames.get(departmentIndex);
                Student student = new Student(studentName, departmentName, tcKimlikNo);
                students.put(student.getStudentId(), student);
                System.out.println("Öğrenci başarıyla eklendi.");
            } else {
                System.out.println("Geçersiz bölüm seçimi.");
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

        public void listStudents(Scanner scanner, DepartmentManager departmentManager) {
            System.out.println("Hangi bölümün öğrencilerini listelemek istersiniz?");
            HashMap<String, String> departmentList = departmentManager.getDepartments();
            ArrayList<String> departmentNames = new ArrayList<>(departmentList.values());

            // Check if the department list is empty
            if (departmentNames.isEmpty()) {
                System.out.println("Hiç bölüm bulunamadı.");
                return; // Return early
            }

            for (int i = 0; i < departmentNames.size(); i++) {
                System.out.println((i + 1) + ". " + departmentNames.get(i));
            }
            int departmentIndex = scanner.nextInt() - 1;
            scanner.nextLine();  // consume newline
            if (departmentIndex >= 0 && departmentIndex < departmentNames.size()) {
                String departmentName = departmentNames.get(departmentIndex);

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
                    System.out.printf("%-20s %-20s %-20s %-20s\n", "Öğrenci No", "Öğrenci Adı","TC Kimlik No", "Mezun mu?");
                    for (Student student : departmentStudents) {
                        System.out.printf("%-20s %-20s %-20s %-20s\n",student.getStudentId(), student.getName(), student.getTcKimlikNo(), student.isGraduated() ? "Evet" : "Hayır");
                    }
                }
            } else {
                System.out.println("Geçersiz bölüm seçimi.");
            }
        }

    public void generateStudentDocument(String ogrenciNo) {
            if (students.containsKey(ogrenciNo)) {
                Student student = students.get(ogrenciNo);
                System.out.println(ogrenciNo + " numaralı öğrenci " + (student.isGraduated() ? "MEZUN" : "AKTİF") + " öğrenci durumundadır. Bilgileri aşağıdaki gibidir:");
                System.out.println("Öğrenci Adı: " + student.getName());
                System.out.println("TC Kimlik No: " + student.getTcKimlikNo());
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