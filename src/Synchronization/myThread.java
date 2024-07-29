package Synchronization;

public class myThread extends Thread{

    private String name;

    public myThread(String name) {
        this.name = name;
    }

    public void run(){
        for(int i=0;i<=2;i++){
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
