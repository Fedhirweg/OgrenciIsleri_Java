import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Teacher.saveIdCounter();
            Student.saveIdCounter();
            Grade.saveGradeCounter();
            Course.saveIdCounter();
        }));

        UserManager userManager = new UserManager();
        Scanner scanner = new Scanner(System.in);
        DepartmentManager departmentManager = new DepartmentManager();

        Student.loadIdCounter();
        Teacher.loadIdCounter();
        Grade.loadGradeCounter();
        Course.loadIdCounter();

        while (true) {
            System.out.println("\n\n\t\t*********************************************");
            System.out.println("\t\t*                                           *");
            System.out.println("\t\t*                                           *");
            System.out.println("\t\t*         OGRENCI ISLERI OTOMASYONU         *");
            System.out.println("\t\t*                                           *");
            System.out.println("\t\t*                                           *");
            System.out.println("\t\t*              \t1. KAYIT OL                 *");
            System.out.println("\t\t*              \t2. GIRIS YAP                *");
            System.out.println("\t\t*              \t3. KULLANICI SIL            *");
            System.out.println("\t\t*              \t0. CIKIS                    *");
            System.out.println("\t\t*                                           *");
            System.out.println("\t\t*********************************************");
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
                case 0:
                    System.out.println("Çıkış Yapılıyor...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Hatalı Seçim.");
            }
            Teacher.saveIdCounter();
            Student.saveIdCounter();
            Grade.saveGradeCounter();
            Course.saveIdCounter();
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
            System.out.println("Giris basarili!");

            String username = loggedInUser.getUsername();
            int usernameLength = username.length();
            int totalLength = 34; // total length of the line
            int remainingSpaces = totalLength - usernameLength - 11; // calculate remaining spaces, 11 is the length of "Hosgeldin, "

            String spacesAfterStr = new String(new char[remainingSpaces]).replace('\0', ' ');

            System.out.println("                                     ");
            System.out.println("        ******************************************");
            System.out.println("        *                                        *");
            System.out.println("        *      Hosgeldin, " + username + spacesAfterStr + "*");
            System.out.println("        *                                        *");
            System.out.println("        ******************************************");
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
                        gradeOperations(scanner, new CourseManager(), new StudentManager(), departmentManager);
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
                    System.out.println("\nÖğrenci Ekleme İşlemi\n");
                    System.out.println("Eklenecek öğrenci adını giriniz:");
                    String addStudentName = scanner.nextLine();
                    studentManager.addStudent(addStudentName, departmentManager, scanner);
                    break;
                case 2:
                    System.out.println("\nÖğrenci Silme İşlemi\n");
                    studentManager.listStudents(scanner, departmentManager);
                    System.out.println("Silinecek öğrenci numarasını giriniz:");
                    String delStudentID = scanner.nextLine();
                    studentManager.deleteStudent(delStudentID);
                    break;
                case 3:
                    System.out.println("\nÖğrenci Arama İşlemi\n");
                    System.out.println("Aranacak öğrenci numarasını giriniz:");
                    String searchStudentName = scanner.nextLine();
                    studentManager.searchStudent(searchStudentName);
                    break;
                case 4:
                    System.out.println("\nÖğrenci Listeleme İşlemi\n");
                    studentManager.listStudents(scanner, departmentManager);
                    break;
                case 5:
                    System.out.println("\nÖğrenci Belgesi İşlemi\n");
                    studentManager.listStudents(scanner, departmentManager);
                    System.out.println("Belgesi oluşturulacak öğrenci numarasını giriniz:");
                    String docStudentName = scanner.nextLine();
                    studentManager.generateStudentDocument(docStudentName);
                    break;
                case 6:
                    System.out.println("\nÖğrenci Mezuniyet İşlemi\n");
                    studentManager.listStudents(scanner, departmentManager);
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
            System.out.println("1. Öğretim Görevlisi Ekle\n2. Öğretim Görevlisi Sil\n3. Öğretim Görevlisi Ara\n4. Öğretim Görevlisi Listele.\n5. Geri");
            teacherChoice = scanner.nextInt();
            scanner.nextLine();  // consume newline
            switch (teacherChoice) {
                case 1:
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
                        teacherManager.addTeacher(addTeacherName, addTeacherDepartment);
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
                    teacherManager.listTeachers();
                    break;
                case 5:
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
            System.out.println("1. Ders Ekle\n2. Ders Sil\n3. Ders Listele\n4. Geri");
            courseChoice = scanner.nextInt();
            scanner.nextLine();
            switch (courseChoice) {
                case 1:
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
                        courseManager.addCourse(new Course( courseName, teacherId, new ArrayList<>(), creditScore));
                    } else {
                        System.out.println("Geçersiz öğretim görevlisi seçimi.");
                    }
                    break;
                case 2:
                    System.out.println("Ders Silme İşlemi");
                    courseManager.listCourses(teacherManager);
                    System.out.println("Silinecek dersin ID'sini giriniz:");
                    String delCourseId = scanner.nextLine();
                    courseManager.deleteCourse(delCourseId);
                    break;
                case 3:
                    System.out.println("Ders Listeleme İşlemi");
                    courseManager.listCourses(teacherManager);
                    break;
                case 4:
                    System.out.println("Ana menüye dönülüyor...");
                    break;
                default:
                    System.out.println("Hatalı seçim.");
            }
        } while (courseChoice != 4);
    }

    public static void gradeOperations(Scanner scanner, CourseManager courseManager, StudentManager studentManager, DepartmentManager departmentManager) {
        GradeManager gradeManager = new GradeManager();
        int gradeChoice;
        do {
            System.out.println("1. Not Ekle\n2. Notları Listele\n3. Not Sil\n4. Geri");
            gradeChoice = scanner.nextInt();
            scanner.nextLine();  // consume newline
            switch (gradeChoice) {
                case 1:
                    ArrayList<Course> courseList = courseManager.getCourses();
                    for (int i = 0; i < courseList.size(); i++) {
                        System.out.println((i + 1) + ". " + courseList.get(i).getCourseName());
                    }
                    System.out.println("Dersi seçiniz:");
                    int courseIndex = scanner.nextInt() - 1;
                    scanner.nextLine();  // consume newline
                    String courseName = courseList.get(courseIndex).getCourseName();

                    ArrayList<Student> studentList = studentManager.getStudents();
                    for (int i = 0; i < studentList.size(); i++) {
                        System.out.println((i + 1) + ". " + studentList.get(i).getName());
                    }
                    System.out.println("Öğrenciyi seçiniz:");
                    int studentIndex = scanner.nextInt() - 1;
                    scanner.nextLine();  // consume newline
                    String studentId = studentList.get(studentIndex).getStudentId();
                    String studentName = studentList.get(studentIndex).getName();

                    System.out.println("Puanı giriniz:");
                    int score = scanner.nextInt();
                    scanner.nextLine();  // consume newline

                    ArrayList<String> departmentList = new ArrayList<>(departmentManager.getDepartments().values());
                    for (int i = 0; i < departmentList.size(); i++) {
                        System.out.println((i + 1) + ". " + departmentList.get(i));
                    }
                    System.out.println("Bölümü seçiniz:");
                    int departmentIndex = scanner.nextInt() - 1;
                    scanner.nextLine();  // consume newline
                    String departmentName = departmentList.get(departmentIndex);

                    Grade grade = new Grade(courseName, studentId, studentName, score, departmentName);
                    gradeManager.addGrade(grade);
                    break;
                case 2:
                    gradeManager.listGrades();
                    break;
                case 3:
                    gradeManager.deleteGrade(scanner);
                    break;
                case 4:
                    System.out.println("Ana menüye dönülüyor...");
                    break;
                default:
                    System.out.println("Hatalı seçim.");
            }
        } while (gradeChoice != 4);
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

