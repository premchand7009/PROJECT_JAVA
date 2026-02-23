import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int choice;

        while(true){
            System.out.println("\n=================================");
            System.out.println("     WELCOME TO JAVA BANK");
            System.out.println("=================================");
            System.out.println("1. User Login");
            System.out.println("2. Create New Account");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("User Login selected");
                    User currentUser = LoginService.userLogin();
                    if(currentUser!=null){
                        BankService.userMenu(currentUser);
                    }
                    break;

                case 2:
                    System.out.println("Create New Account selected");
                    BankService.createAccount();
                    break;

                case 3:
                    System.out.println("Admin Login selected");
                    AdminService.adminLogin();
                    break;

                case 4:
                    System.out.println("Thank you for using JAVA BANK ");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Try again.");
            }
            
        }
    }
}