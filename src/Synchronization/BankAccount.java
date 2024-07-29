package Synchronization;

public class BankAccount {

    private double balance;

    private String name;

    public String getName() {
        return name;
    }

    public  void setName(String name) {
         synchronized (this.name) {
            this.name = name;
            System.out.println("Name changed to: "+name);
        }
//        this.name = name;
//        System.out.println("Name changed to: "+name);
    }

    public BankAccount(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount){
        try {
            System.out.println("Chit chatting");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        synchronized (this) {
            double originalAmount = balance;
            balance += amount;
            System.out.println("Amount added: " + amount +" new balance: "+balance);
        }

    }
    public synchronized void withdraw(double amount){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        double originalAmount = balance;
        if (amount<=balance){
            balance -= amount;
            System.out.println("Amount withdrawn: " + amount +" new balance: "+balance);
        }else{
            System.out.println("Insufficient funds");
        }
    }
}
