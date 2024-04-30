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
            System.out.println("Kullanıcı adı boş olamaz.");
            return;
        }

        if (password == null || password.isEmpty()) {
            System.out.println("Şifre boş olamaz.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            System.out.println("Şifreler uyuşmuyor.");
            return;
        }

        if (password.length() < 8) {
            System.out.println("Şifre en az 8 karakter olmalıdır.");
            return;
        }

        if (users.containsKey(username)) {
            System.out.println("Kullanıcı adı zaten mevcut.");
            return;
        }

        users.put(username, new User(username, password));
        System.out.println("Kullanıcı başarıyla oluşturuldu.");
    }

    public User loginUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.checkPassword(password)) {
            System.out.println("Giriş başarılı.");
            return user;
        } else {
            System.out.println("Kullanıcı adı veya şifre hatalı.");
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
            System.out.println("Kullanıcı başarıyla silindi.");
        } else {
            System.out.println("Kullanıcı mevcut değil.");
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
            System.out.println("Kullanıcı kaydı bulunamadı.");
        } catch (ClassNotFoundException c) {
            System.out.println("Class bulunamadı");
            c.printStackTrace();
        }
    }
}