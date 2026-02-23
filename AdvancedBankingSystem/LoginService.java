import java.util.ArrayList;
import java.util.Scanner;

public class LoginService {
    
    public static User userLogin() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter Account Number:");
        int accNo = sc.nextInt();

        System.out.print("Enter Password: ");
        String password = sc.next();

        ArrayList<User> users = FileHandler.readUsers();

        for (User u:users){
            if (u.getAccountNo() == accNo && u.getPassword().equals(password)){
                if (u.getStatus().equalsIgnoreCase("BLOCKED")){
                    System.out.println("Account is BLOCKED. Contact bank.");
                    return null;
                }
                System.out.println("Login Successful! Welcome " + u.getName());
                return u; 
            }
        }
        System.out.println("Invalid Account Number or Password!");
        return null;
    }
}
