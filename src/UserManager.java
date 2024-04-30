import java.util.HashMap;
import java.io.*;

public class UserManager {
    private HashMap<String, User> users = new HashMap<>();
    private static final String USER_FILE = "users.dat";

    public UserManager() {
        loadUsers();
    }

    public void registerUser(String username, String password, String confirmPassword) {
        if (username == null || username.isEmpty()) {
            System.out.println("Username cannot be empty.");
            return;
        }

        if (password == null || password.isEmpty()) {
            System.out.println("Password cannot be empty.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match.");
            return;
        }

        if (password.length() < 8) {
            System.out.println("Password must be at least 8 characters long.");
            return;
        }

        if (users.containsKey(username)) {
            System.out.println("Username already exists.");
            return;
        }

        users.put(username, new User(username, password));
        System.out.println("User registered successfully.");
    }

    public User loginUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.checkPassword(password)) {
            System.out.println("Login successful.");
            return user;
        } else {
            System.out.println("Invalid username or password.");
            return null;
        }
    }

    public void saveUsers() {
        try {
            FileOutputStream fos = new FileOutputStream(USER_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(users);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void deleteUser(String username) {
        if (users.containsKey(username)) {
            users.remove(username);
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User does not exist.");
        }
    }

    @SuppressWarnings("unchecked")
    public void loadUsers() {
        try {
            FileInputStream fis = new FileInputStream(USER_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            users = (HashMap<String, User>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            System.out.println("No previous user data found.");
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
    }
}