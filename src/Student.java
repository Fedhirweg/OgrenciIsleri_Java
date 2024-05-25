import java.io.*;

public class Student implements Serializable {
    public static int idCounter = 1;
    private final String name;
    private final String department;
    private boolean graduated;
    private final String studentId;
    private final String tcKimlikNo;

    public Student(String name, String department, String tcKimlikNo) {
        this.name = name;
        this.department = department;
        this.tcKimlikNo = tcKimlikNo;
        this.graduated = false;
        this.studentId = "S" + idCounter++;
    }

    public static void saveIdCounter() {
        try {
            FileOutputStream fos = new FileOutputStream("studentIdCounter.dat");
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
            FileInputStream fis = new FileInputStream("studentIdCounter.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            idCounter = ois.readInt();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            idCounter = 0;
        }
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getTcKimlikNo() {
        return tcKimlikNo;
    }

    public boolean isGraduated() {
        return graduated;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setGraduated(boolean graduated) {
        this.graduated = graduated;
    }
}