import java.io.Serializable;

public class Teacher implements Serializable {
    private final String id;
    private final String name;
    private final String department;

    public Teacher(String id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }
}