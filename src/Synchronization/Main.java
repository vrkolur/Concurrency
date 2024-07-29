package Synchronization;

public class Main {

    public static void main(String[] args) {

        BankAccount varunAccount = new BankAccount("varun", 10000);

        Thread thread1 = new Thread(()-> varunAccount.withdraw(5000));
        Thread thread2 = new Thread(()-> varunAccount.deposit(15000));
        Thread thread3 = new Thread(()-> varunAccount.setName("Sha"));
        Thread thread4 = new Thread(()-> varunAccount.withdraw(3000));

        thread1.start();
        thread2.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        thread3.start();
        thread4.start();

        try{
            thread2.join();
            thread1.join();
            thread3.join();
            thread4.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Final Balance: "+varunAccount.getBalance());
    }
}
