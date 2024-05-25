import java.io.*;

public class Teacher implements Serializable {
    private static int idCounter = 1;
    private final String name;
    private final String department;
    private final String teacherId;

    public Teacher(String name, String department) {
        this.name = name;
        this.department = department;
        this.teacherId = "T" + idCounter++;
    }

    public static void saveIdCounter() {
        try {
            FileOutputStream fos = new FileOutputStream("teacherIdCounter.dat");
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
            FileInputStream fis = new FileInputStream("teacherIdCounter.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            idCounter = ois.readInt();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            idCounter = 0;
        }
    }

    public String getId() {
        return teacherId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }
}