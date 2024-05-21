import java.util.ArrayList;
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
                scanner.nextLine();
                switch (menuChoice) {
                    case 1:
                        System.out.println("Öğrenci İşlemleri");
                        studentOperations(scanner, departmentManager, studentManager);
                        break;
                    case 2:
                        System.out.println("Öğretim Görevlisi İşlemleri");
                        teacherOperations(scanner, departmentManager);
                        break;
                    case 3:
                        System.out.println("Ders İşlemleri");
                        courseOperations(scanner, new CourseManager(), new TeacherManager());
                        break;
                    case 4:
                        System.out.println("Not İşlemleri");
                        gradeOperations(scanner, new CourseManager(), new StudentManager());
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
                    studentManager.listStudents(scanner, departmentManager);
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

    public static void teacherOperations(Scanner scanner, DepartmentManager departmentManager) {
        TeacherManager teacherManager = new TeacherManager();
        int teacherChoice;
        do {
            System.out.println("1. Öğretim Görevlisi Ekle\n2. Öğretim Görevlisi Sil\n3. Öğretim Görevlisi Ara\n4. Geri");
            teacherChoice = scanner.nextInt();
            scanner.nextLine();  // consume newline
            switch (teacherChoice) {
                case 1:
                    System.out.println("Eklenecek öğretim görevlisi numarasını giriniz:");
                    String addTeacherId = scanner.nextLine();
                    System.out.println("Eklenecek öğretim görevlisi adını giriniz:");
                    String addTeacherName = scanner.nextLine();
                    ArrayList<String> departmentList = new ArrayList<>(departmentManager.getDepartments().values());
                    for (int i = 0; i < departmentList.size(); i++) {
                        System.out.println((i + 1) + ". " + departmentList.get(i));
                    }
                    System.out.println("Öğretim görevlisi bölümünü seçiniz:");
                    int departmentIndex = scanner.nextInt() - 1;
                    scanner.nextLine();  // consume newline

                    if (departmentIndex >= 0 && departmentIndex < departmentList.size()) {
                        String addTeacherDepartment = departmentList.get(departmentIndex);
                        teacherManager.addTeacher(addTeacherId, addTeacherName, addTeacherDepartment);
                    } else {
                        System.out.println("Geçersiz bölüm seçimi.");
                    }
                    break;
                case 2:
                    ArrayList<Teacher> teacherList = teacherManager.getTeachers();
                    for (int i = 0; i < teacherList.size(); i++) {
                        System.out.println((i + 1) + ". " + teacherList.get(i).getName());
                    }
                    System.out.println("Silinecek öğretim görevlisini seçiniz:");
                    int teacherIndex = scanner.nextInt() - 1;
                    scanner.nextLine();  // consume newline

                    if (teacherIndex >= 0 && teacherIndex < teacherList.size()) {
                        String delTeacherId = teacherList.get(teacherIndex).getId();
                        teacherManager.deleteTeacher(delTeacherId);
                    } else {
                        System.out.println("Geçersiz öğretim görevlisi seçimi.");
                    }
                    break;
                case 3:
                    System.out.println("Aranacak öğretim görevlisi numarasını giriniz:");
                    String searchTeacherId = scanner.nextLine();
                    teacherManager.searchTeacher(searchTeacherId);
                    break;
                case 4:
                    System.out.println("Ana menüye dönülüyor...");
                    break;
                default:
                    System.out.println("Hatalı seçim.");
            }
        } while (teacherChoice != 4);
    }

    public static void courseOperations(Scanner scanner, CourseManager courseManager, TeacherManager teacherManager) {
        int courseChoice;
        do {
            System.out.println("1. Ders Ekle\n2. Ders Sil\n3. Ders Güncelle\n4. Dersleri Listele\n5. Geri");
            courseChoice = scanner.nextInt();
            scanner.nextLine();
            switch (courseChoice) {
                case 1:
                    System.out.println("Eklenecek dersin ID'sini giriniz:");
                    String courseId = scanner.nextLine();
                    System.out.println("Eklenecek dersin adını giriniz:");
                    String courseName = scanner.nextLine();
                    ArrayList<Teacher> teacherList = teacherManager.getTeachers();
                    for (int i = 0; i < teacherList.size(); i++) {
                        System.out.println((i + 1) + ". " + teacherList.get(i).getName());
                    }
                    System.out.println("Dersin öğretmenini seçiniz:");
                    int teacherIndex = scanner.nextInt() - 1;
                    scanner.nextLine();  // consume newline
                    if (teacherIndex >= 0 && teacherIndex < teacherList.size()) {
                        String teacherId = teacherList.get(teacherIndex).getId();
                        System.out.println("Dersin kredi puanını giriniz:");
                        int creditScore = scanner.nextInt();
                        scanner.nextLine();  // consume newline
                        courseManager.addCourse(new Course(courseId, courseName, teacherId, new ArrayList<>(), creditScore));
                    } else {
                        System.out.println("Geçersiz öğretim görevlisi seçimi.");
                    }
                    break;
                case 2:
                    System.out.println("Silinecek dersin ID'sini giriniz:");
                    String delCourseId = scanner.nextLine();
                    courseManager.deleteCourse(delCourseId);
                    break;
                case 3:
                    System.out.println("Güncellenecek dersin ID'sini giriniz:");
                    String updateCourseId = scanner.nextLine();
                    System.out.println("Dersin yeni adını giriniz:");
                    String updateCourseName = scanner.nextLine();
                    System.out.println("Dersin yeni öğretmen ID'sini giriniz:");
                    String updateTeacherId = scanner.nextLine();
                    System.out.println("Dersin yeni kredi puanını giriniz:");
                    int updateCreditScore = scanner.nextInt();
                    scanner.nextLine();  // consume newline
                    courseManager.updateCourse(new Course(updateCourseId, updateCourseName, updateTeacherId, new ArrayList<>(), updateCreditScore));
                    break;
                case 4:
                    courseManager.listCourses();
                    break;
                case 5:
                    System.out.println("Ana menüye dönülüyor...");
                    break;
                default:
                    System.out.println("Hatalı seçim.");
            }
        } while (courseChoice != 5);
    }

    public static void gradeOperations(Scanner scanner, CourseManager courseManager, StudentManager studentManager) {
        System.out.println("Not İşlemleri");
    }

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
                    departmentManager.deleteDepartment(scanner);
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

