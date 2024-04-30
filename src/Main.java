import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Register\n2. Login\n3. Delete User\n4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter username:");
                    String regUsername = scanner.nextLine();
                    System.out.println("Enter password:");
                    String regPassword = scanner.nextLine();
                    System.out.println("Confirm password:");
                    String confirmPassword = scanner.nextLine();
                    userManager.registerUser(regUsername, regPassword, confirmPassword);
                    break;
                case 2:
                    System.out.println("Enter username:");
                    String loginUsername = scanner.nextLine();
                    System.out.println("Enter password:");
                    String loginPassword = scanner.nextLine();
                    User loggedInUser = userManager.loginUser(loginUsername, loginPassword);
                    if (loggedInUser != null) {
                        System.out.println("Welcome, " + loggedInUser.getUsername() + "!");
                        int menuChoice;
                        do {
                            displayMenu();
                            menuChoice = scanner.nextInt();
                            scanner.nextLine();  // consume newline
                            switch (menuChoice) {
                                case 1:
                                    System.out.println("Öğrenci İşlemleri");
                                    // Add your code for Öğrenci İşlemleri here
                                    break;
                                case 2:
                                    System.out.println("Öğretim Görevlisi İşlemleri");
                                    // Add your code for Öğretim Görevlisi İşlemleri here
                                    break;
                                case 3:
                                    System.out.println("Ders İşlemleri");
                                    // Add your code for Ders İşlemleri here
                                    break;
                                case 4:
                                    System.out.println("Not İşlemleri");
                                    // Add your code for Not İşlemleri here
                                    break;
                                case 5:
                                    System.out.println("Bölüm İşlemleri");
                                    DepartmentManager departmentManager = new DepartmentManager();
                                    int departmentChoice;
                                    do {
                                        System.out.println("1. Bölüm Ekleme\n2. Bölüm Listeleme\n3. Bölüm Silme\n4. Geri");
                                        departmentChoice = scanner.nextInt();
                                        scanner.nextLine();  // consume newline
                                        switch (departmentChoice) {
                                            case 1:
                                                System.out.println("Enter department name to add:");
                                                String addDepartmentName = scanner.nextLine();
                                                departmentManager.addDepartment(addDepartmentName);
                                                break;
                                            case 2:
                                                departmentManager.listDepartments();
                                                break;
                                            case 3:
                                                System.out.println("Enter department name to delete:");
                                                String delDepartmentName = scanner.nextLine();
                                                departmentManager.deleteDepartment(delDepartmentName);
                                                break;
                                            case 4:
                                                System.out.println("Returning to main menu...");
                                                break;
                                            default:
                                                System.out.println("Invalid choice.");
                                        }
                                    } while (departmentChoice != 4);
                                    break;
                                case 6:
                                    System.out.println("Exiting to user menu...");
                                    break;
                                default:
                                    System.out.println("Invalid choice.");
                            }
                        } while (menuChoice != 6);
                    }
                    break;
                case 3:
                    System.out.println("Enter username to delete:");
                    String delUsername = scanner.nextLine();
                    userManager.deleteUser(delUsername);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    userManager.saveUsers();
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void displayMenu() {
        System.out.println("Öğrenci İşlemleri Otomasyonu");
        System.out.println("1. Öğrenci İşlemleri");
        System.out.println("2. Öğretim Görevlisi İşlemleri");
        System.out.println("3. Ders İşlemleri");
        System.out.println("4. Not İşlemleri");
        System.out.println("5. Bölüm İşlemleri");
        System.out.println("6. Oturumu Kapat");
    }
}

