public class User {

    private int accountNo;
    private String name;
    private String password;
    private double balance;
    private String status; 

    public User(int accountNo, String name, String password, double balance, String status) {
        this.accountNo = accountNo;
        this.name = name;
        this.password = password;
        this.balance = balance;
        this.status = status;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public String getStatus() {
        return status;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
