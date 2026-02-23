public class Transaction {
    private int accNo;
    private String type;
    private double amount;

    Transaction (int accNo, String type, double amount){
        this.accNo = accNo;
        this.type = type;
        this.amount = amount;
    }
    public int getAccNo() { 
        return accNo; 
    }
    public String getType() { 
        return type; 
    }
    public double getAmount() {
        return amount; 
    }
}
