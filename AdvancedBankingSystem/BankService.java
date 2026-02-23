import java.util.ArrayList;
import java.util.Scanner;

public class BankService {

    public static void userMenu(User currentUser) {

        Scanner sc = new Scanner(System.in);
        int choice;

        while (true){
            System.out.println("\n========== USER MENU ==========");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Get Transactions");
            System.out.println("5. Change Password");
            System.out.println("6. Fund Transfer");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    checkBalance(currentUser);
                    break;

                case 2:
                    depositMoney(currentUser);
                    break;

                case 3:
                    withdrawMoney(currentUser);
                    break;

                case 4:
                    showTransactions(currentUser);
                    break;
                
                case 5:
                    changePassword(currentUser);
                    break;

                case 6:
                    fundTransfer(currentUser);
                    break;
                    
                case 7:
                    System.out.println("Logged out successfully.");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    public static void checkBalance(User user){
        System.out.println("Current Balance: ₹" + user.getBalance());
    }
    public static void depositMoney(User user){
        Scanner sc = new Scanner (System.in);
        System.out.println("Enter amount to deposit:");
        double amount = sc.nextDouble();

        if(amount<0){
            System.out.println("Invalid amount!");
            return;
        }
        ArrayList<User> users = FileHandler.readUsers();

        for (User u:users){
            if (u.getAccountNo()==user.getAccountNo()){
                u.setBalance(u.getBalance()+amount);
                user.setBalance(u.getBalance());
                break;
            }
        }
        FileHandler.writeTransaction(user.getAccountNo(), "Deposit", amount);
        FileHandler.writeUsers(users);
        System.out.println("Deposit successful!");
    }
    public static void withdrawMoney(User user){
        Scanner sc = new Scanner (System.in);
        System.out.println("Enter amount to withdraw:");
        double amount=sc.nextDouble();

        if(amount<0){
            System.out.println("Invalid amount!");
            return;
        }
        if(amount>user.getBalance()){
            System.out.println("Insufficient balance!");
            return;
        }
        ArrayList<User> users = FileHandler.readUsers();

        for (User u : users) {
            if (u.getAccountNo() == user.getAccountNo()) {
                u.setBalance(u.getBalance() - amount);
                user.setBalance(u.getBalance());
                break;
            }
        }

        FileHandler.writeUsers(users);
        FileHandler.writeTransaction(user.getAccountNo(), "Withdraw", amount);

        System.out.println("Withdrawal successful!");

    }
    public static void createAccount() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n===== CREATE NEW ACCOUNT =====");

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        sc.nextLine(); // clear buffer

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Set Password: ");
        String password = sc.nextLine();

        System.out.print("Enter Initial Deposit: ");
        double balance = sc.nextDouble();

        if (balance <= 0) {
            System.out.println("Initial balance cannot be negative!");
            return;
        }
        ArrayList<User> users = FileHandler.readUsers();

        for (User u : users) {
            if (u.getAccountNo() == accNo) {
                System.out.println("Account number already exists!");
                return;
            }
        }
        User newUser = new User(accNo, name, password, balance, "ACTIVE");
        users.add(newUser);
        FileHandler.writeUsers(users);

        System.out.println("Account created successfully!");
        
    }
    public static void showTransactions(User user) {

        ArrayList<Transaction> list = FileHandler.readTransactions();

        if (list.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        boolean found = false;

        System.out.println("\n--- YOUR TRANSACTIONS ---");

        for (Transaction t : list) {
            if (t.getAccNo() == user.getAccountNo()) {
                System.out.println(t.getType() + " | ₹" + t.getAmount());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No transactions for this account.");
        }
    }

    public static void changePassword(User user) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter old password: ");
        String oldPass = sc.nextLine();

        if (!user.getPassword().equals(oldPass)) {
            System.out.println("Incorrect old password.");
            return;
        }

        System.out.print("Enter new password: ");
        String newPass = sc.nextLine();
        if (newPass.trim().isEmpty()) {
            System.out.println("Password cannot be empty.");
            return;
        }

        ArrayList<User> users = FileHandler.readUsers();
        for (User u : users) {
            if (u.getAccountNo() == user.getAccountNo()) {
                u.setPassword(newPass);
                user.setPassword(newPass);
                break;
            }
        }
        FileHandler.writeUsers(users);
        System.out.println("Password changed successfully!");
    }
    public static void fundTransfer(User sender) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter receiver account number: ");
        int receiverAcc = sc.nextInt();

        System.out.print("Enter amount to transfer: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        if (amount > sender.getBalance()) {
            System.out.println("Insufficient balance.");
            return;
        }
        ArrayList<User> users = FileHandler.readUsers();
        User receiver=null;
        for (User u:users) {
            if(u.getAccountNo() == receiverAcc) {
                receiver = u;
            }
        }
        if (receiver == null) {
            System.out.println("Receiver not found.");
            return;
        }
        for (User u : users) {
            if (u.getAccountNo() == sender.getAccountNo()) {
                u.setBalance(u.getBalance() - amount);
                sender.setBalance(u.getBalance());
            }
        }

        receiver.setBalance(receiver.getBalance() + amount);

        FileHandler.writeUsers(users);

        FileHandler.writeTransaction(sender.getAccountNo(), "Transfer Out", amount);
        FileHandler.writeTransaction(receiver.getAccountNo(), "Transfer In", amount);

        System.out.println("Transfer successful!");

    }
}