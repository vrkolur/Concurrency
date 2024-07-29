import java.util.concurrent.TimeUnit;
// This is an example of memory Consistent Errors-> OS may read from heap variables and make a copy of the value and hence result in inconsistency
// Change might not be immediately reflected on the heap, update only in the thread local cache.
public class ChachedData {

//    private boolean flag = false;

    private volatile boolean flag = false;
//    volatile means that this variable's value can be changed by multiple threads, also ensures the variable is always read and written into the main memory rather than thread specific memory.

    public void toggelFlag(){
        flag = !flag;
    }

    public boolean isReady(){
        return flag;
    }

    public static void main(String[] args) {
        ChachedData example = new ChachedData();

        Thread writerThread = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            example.toggelFlag();;
            System.out.println("A. Flag is set to: "+ example.isReady());
        });

        Thread readerThread = new Thread(()->{
            while(!example.isReady()){
//                Busy wait
            }
            System.out.println("B. Flag is set to: "+ example.isReady());
        });
        writerThread.start();
        readerThread.start();
    }
}
