import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        Scanner scanner = new Scanner(System.in);
        DepartmentManager departmentManager = new DepartmentManager();

        while (true) {
            System.out.println("1. Kayıt ol\n2. Giriş yap\n3. Kullanıcı kaydı sil\n4. Çıkış yap");
            int choice = scanner.nextInt();
            scanner.nextLine(); // yenisatir karakterini tüketir

            switch (choice) {
                case 1:
                    System.out.println("Kullanıcı Adı Giriniz:");
                    String regUsername = scanner.nextLine();
                    System.out.println("Şifre Giriniz:");
                    String regPassword = scanner.nextLine();
                    System.out.println("Şifreyi Doğrulayın:");
                    String confirmPassword = scanner.nextLine();
                    userManager.registerUser(regUsername, regPassword, confirmPassword);
                    break;
                case 2:
                    loginUserMenu(userManager, scanner, departmentManager);
                    break;
                case 3:
                    System.out.println("Silmek İstediğiniz Kullanıcı Adını Giriniz:");
                    String delUsername = scanner.nextLine();
                    userManager.deleteUser(delUsername);
                    break;
                case 4:
                    System.out.println("Çıkış Yapılıyor...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Hatalı Seçim.");
            }
        }
    }

    public static void loginUserMenu(UserManager userManager, Scanner scanner, DepartmentManager departmentManager) {
        System.out.println("Kullanıcı Adı Giriniz:");
        String loginUsername = scanner.nextLine();
        System.out.println("Şifre Giriniz:");
        String loginPassword = scanner.nextLine();
        User loggedInUser = userManager.loginUser(loginUsername, loginPassword);
        StudentManager studentManager = null;
        if (loggedInUser != null) {
            System.out.println("Hoşgeldiniz, " + loggedInUser.getUsername() + "!");
            int menuChoice;
            do {
                System.out.println("1. Öğrenci İşlemleri\n2. Öğretim Görevlisi İşlemleri\n3. Ders İşlemleri\n4. Not İşlemleri\n5. Bölüm İşlemleri\n6. Kullanıcı Menüsüne Dön\n7. Çıkış Yap");
                menuChoice = scanner.nextInt();
                scanner.nextLine();  // yenisatir karakterini tüketir
                switch (menuChoice) {
                    case 1:
                        studentOperations(scanner, departmentManager, studentManager);
                        break;
                    case 2:
                        System.out.println("Öğretim Görevlisi İşlemleri");
                        teacherOperations();
                        break;
                    case 3:
                        System.out.println("Ders İşlemleri");
                        break;
                    case 4:
                        System.out.println("Not İşlemleri");
                        break;
                    case 5:
                        System.out.println("Bölüm İşlemleri");
                        departmentOperations(scanner, departmentManager);
                        break;
                    case 6:
                        System.out.println("Kullanıcı Menüsüne Dönülüyor...");
                        break;
                    case 7:
                        System.out.println("Çıkış Yapılıyor...");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Hatalı Seçim.");
                }
            } while (menuChoice != 6);
        }
    }

    public static void studentOperations(Scanner scanner, DepartmentManager departmentManager, StudentManager studentManager) {
        System.out.println("Öğrenci İşlemleri");
        studentManager = new StudentManager();
        int studentChoice;
        do {
            System.out.println("1. Öğrenci Ekle\n2. Öğrenci Sil\n3. Öğrenci Ara\n4. Öğrenci Listele\n5. Öğrenci Belgesi\n6. Öğrenci Mezuniyet \n7. Geri");
            studentChoice = scanner.nextInt();
            scanner.nextLine();  // consume newline
            switch (studentChoice) {
                case 1:
                    System.out.println("Eklenecek öğrenci adını giriniz:");
                    String addStudentName = scanner.nextLine();
                    System.out.println("Öğrenci numarasını giriniz:");
                    String ogrenciNo = scanner.nextLine();
                    studentManager.addStudent(addStudentName, ogrenciNo, departmentManager, scanner);
                    break;
                case 2:
                    System.out.println("Silinecek öğrenci adını giriniz:");
                    String delStudentName = scanner.nextLine();
                    studentManager.deleteStudent(delStudentName);
                    break;
                case 3:
                    System.out.println("Aranacak öğrenci adını giriniz:");
                    String searchStudentName = scanner.nextLine();
                    studentManager.searchStudent(searchStudentName);
                    break;
                case 4:
                    studentManager.listStudents(scanner);
                    break;
                case 5:
                    System.out.println("Belgesi oluşturulacak öğrenci adını giriniz:");
                    String docStudentName = scanner.nextLine();
                    studentManager.generateStudentDocument(docStudentName);
                    break;
                case 6:
                    System.out.println("Mezuniyet işlemi yapılacak öğrenci numarasını giriniz:");
                    String graduateStudentId = scanner.nextLine();
                    studentManager.graduateStudent(graduateStudentId);
                    break;
                case 7:
                    System.out.println("Ana menüye dönülüyor...");
                    break;
                default:
                    System.out.println("Hatalı seçim.");
            }
        } while (studentChoice != 7);
    }

    public static void teacherOperations() {}

    public static void courseOperations() {}

    public static void gradeOperations() {}

    public static void departmentOperations(Scanner scanner, DepartmentManager departmentManager) {
        int departmentChoice;
        do {
            System.out.println("1. Bölüm Ekleme\n2. Bölüm Listeleme\n3. Bölüm Silme\n4. Geri");
            departmentChoice = scanner.nextInt();
            scanner.nextLine();  // consume newline
            switch (departmentChoice) {
                case 1:
                    System.out.println("Eklenecek bölüm adını giriniz:");
                    String addDepartmentName = scanner.nextLine();
                    departmentManager.addDepartment(addDepartmentName);
                    break;
                case 2:
                    departmentManager.listDepartments();
                    break;
                case 3:
                    System.out.println("Silinecek bölüm adını giriniz:");
                    String delDepartmentName = scanner.nextLine();
                    departmentManager.deleteDepartment(delDepartmentName);
                    break;
                case 4:
                    System.out.println("Ana menüye dönülüyor...");
                    break;
                default:
                    System.out.println("Hatalı seçim.");
            }
        } while (departmentChoice != 4);
    }


}

