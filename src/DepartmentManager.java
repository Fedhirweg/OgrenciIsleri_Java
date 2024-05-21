import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.util.Scanner;

public class DepartmentManager {
    private HashMap<String, String> departments = new HashMap<>();
    private static final String DEPARTMENT_FILE = "departments.dat";

    public DepartmentManager() {
        loadDepartments();
    }

    public void addDepartment(String departmentName) {
        if (departments.containsKey(departmentName)) {
            System.out.println("Bölüm zaten mevcut.");
        } else {
            departments.put(departmentName, departmentName);
            System.out.println("Bölüm başarıyla eklendi.");
            saveDepartments();
        }
    }

    public void listDepartments() {
        if (departments.isEmpty()) {
            System.out.println("Hiç bölüm bulunamadı.");
        } else {
            System.out.println("Bölümler:");
            for (String departmentName : departments.keySet()) {
                System.out.println(departmentName);
            }
        }
    }

    public void deleteDepartment(Scanner scanner) {
        if (departments.isEmpty()) {
            System.out.println("Hiç bölüm bulunamadı.");
        } else {
            ArrayList<String> departmentNames = new ArrayList<>(departments.values());
            for (int i = 0; i < departmentNames.size(); i++) {
                System.out.println((i + 1) + ". " + departmentNames.get(i));
            }
            System.out.println("Silinecek bölümü seçiniz:");
            int departmentIndex = scanner.nextInt() - 1;
            scanner.nextLine();  // consume newline
            if (departmentIndex >= 0 && departmentIndex < departmentNames.size()) {
                String departmentName = departmentNames.get(departmentIndex);
                departments.remove(departmentName);
                System.out.println("Bölüm başarıyla silindi.");
                saveDepartments();
            } else {
                System.out.println("Geçersiz bölüm seçimi.");
            }
        }
    }
    public void saveDepartments() {
        try {
            FileOutputStream fos = new FileOutputStream(DEPARTMENT_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(departments);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadDepartments() {
        try {
            FileInputStream fis = new FileInputStream(DEPARTMENT_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            departments = (HashMap<String, String>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            System.out.println("Hiç kayıtlı bölüm bulunamadı.");
        } catch (ClassNotFoundException c) {
            System.out.println("Class bulunamadı");
            c.printStackTrace();
        }
    }

    public boolean departmentExists(String departmentName) {
        return departments.containsKey(departmentName);
    }

    public HashMap<String, String> getDepartments() {
        return departments;
    }
}