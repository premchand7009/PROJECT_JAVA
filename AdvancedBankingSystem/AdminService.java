import java.util.ArrayList;
import java.util.Scanner;

public class AdminService {

    private static final String ADMIN_ID = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    public static void adminLogin(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Admin ID:");
        String admin_id = sc.nextLine();

        System.out.println("Enter Password:");
        String password = sc.nextLine();

        if (admin_id.equals(ADMIN_ID) && password.equals(ADMIN_PASSWORD)){
            System.out.println("Admin Log-in Successful!");
            admin_Menu();
        }else{
            System.out.println("Invalid Admin Credentials!");
        }
    }
    public static void admin_Menu() {

        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n========= ADMIN MENU =========");
            System.out.println("1. View All Accounts");
            System.out.println("2. Search Account");
            System.out.println("3. Block Account");
            System.out.println("4. Active Account");
            System.out.println("5. View Bank Transanctions");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    view_all_acounts();
                    break;
                case 2:
                    search_acounts();
                    break;
                case 3:
                    block_accounts();
                    break;
                case 4:
                    active_accounts();
                    break;
                case 5:
                    getTransactions();
                    break;
                case 6:
                    System.out.println("Admin Logged Out");
                    return;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
    public static void view_all_acounts(){

        ArrayList<User> users = FileHandler.readUsers();
        System.out.println("\n ---All Bank Acounts---");
        for (User u:users){
            System.out.println(
                u.getAccountNo()+" | "+
                u.getName() + " | " +
                u.getBalance() + " | " +
                u.getStatus()
            );
        }
    }
    public static void search_acounts(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter account number to seaarch:");
        int accNo = sc.nextInt();
        ArrayList<User> users = FileHandler.readUsers();

        for (User u : users) {
            if (u.getAccountNo() == accNo) {
                System.out.println("Account Found:");
                System.out.println(
                    u.getAccountNo() + " | " +
                    u.getName() + " | " +
                    u.getBalance() + " | " +
                    u.getStatus()
                );
                return;
            }
        }

        System.out.println("Account not found!");
    }
    public static void block_accounts(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter account number to block:");
        int accNo = sc.nextInt();
        ArrayList<User> users = FileHandler.readUsers();

        for (User u : users) {
            if (u.getAccountNo() == accNo) {
                u.setStatus("BLOCKED");
                FileHandler.writeUsers(users);
                System.out.println("Account BLOCKED successfully!");
                return;
            }
        }
        System.out.println("Account not found!");
    }
    public static void active_accounts(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter account number to active:");
        int accNo = sc.nextInt();
        ArrayList<User> users = FileHandler.readUsers();

        for (User u : users) {
            if (u.getAccountNo() == accNo) {
                if (u.getStatus() == "ACTIVE"){
                    System.out.println("Account is already ACTIVE!");
                    return;
                }
                u.setStatus("ACTIVE");
                FileHandler.writeUsers(users);
                System.out.println("Account ACTIVE successfully!");
                return;
            }
        }
        System.out.println("Account not found!");
    }
    public static void getTransactions() {

        Scanner sc = new Scanner(System.in);
        ArrayList<Transaction> trns = FileHandler.readTransactions();

        if (trns.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        int total = trns.size();
        System.out.println("Total transactions in bank: " + total);

        System.out.print("How many transactions to display? ");
        int j = sc.nextInt();

        if (j <= 0) {
            System.out.println("Number must be greater than 0.");
            return;
        }

        if (j > total) {
            System.out.println("⚠ Only " + total + " transactions available. Showing all.");
            j = total;
        }

        System.out.println("\n--- TRANSACTIONS ---");

        for (int k = 0; k < j; k++) {
            Transaction t = trns.get(k);
            System.out.println(
                t.getAccNo() + " | " +
                t.getType() + " | ₹" +
                t.getAmount()
            );
        }
    }

}
