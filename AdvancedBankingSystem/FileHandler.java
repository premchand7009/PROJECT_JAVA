import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    private static final String USER_FILE = "data/users.txt";
    private static final String TRANSACTION_FILE = "data/transactions.txt";

    public static ArrayList<User> readUsers() {

        ArrayList<User> users = new ArrayList<>();
        File file = new File(USER_FILE);

        if (!file.exists()) {
            return users;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                int accNo = Integer.parseInt(data[0]);
                String name = data[1];
                String password = data[2];
                double balance = Double.parseDouble(data[3]);
                String status = data[4];

                users.add(new User(accNo, name, password, balance, status));
            }

        } catch (IOException e) {
            System.out.println("⚠ Error reading users file.");
            e.printStackTrace();
        }

        return users;
    }

    public static void writeUsers(ArrayList<User> users) {

        try {
            File file = new File(USER_FILE);

            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                for (User u : users) {
                    bw.write(
                            u.getAccountNo() + "," +
                            u.getName() + "," +
                            u.getPassword() + "," +
                            u.getBalance() + "," +
                            u.getStatus()
                    );
                    bw.newLine();
                }
            }

            System.out.println("DEBUG: users.txt created/updated");

        } catch (IOException e) {
            System.out.println("⚠ Error writing users file.");
            e.printStackTrace();
        }
    }

    public static void writeTransaction(int accNo, String type, double amount) {

        try {
            File file = new File(TRANSACTION_FILE);

            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
                bw.write(accNo + "," + type + "," + amount);
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("⚠ Error writing transaction file.");
            e.printStackTrace();
        }
    }
    public static ArrayList<Transaction> readTransactions(){

        ArrayList<Transaction> trns = new ArrayList<>();
        File file = new File(TRANSACTION_FILE);
        if (!file.exists()){
            return trns;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                int accNo = Integer.parseInt(data[0]);
                String type = data[1];
                double amount = Double.parseDouble(data[2]);

                trns.add(new Transaction(accNo, type, amount));
            }
            
        } catch (Exception e) {
            System.out.println("Error reading transactions");
        }

        return trns;
    }
}
