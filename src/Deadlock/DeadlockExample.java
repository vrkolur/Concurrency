package Deadlock;
/**
 * This class demonstrates a deadlock scenario where two threads are waiting for
 * locks held by each other, resulting in a deadlock.
 */
/**
 * To avoid deadlock, ensure that locks are acquired in a consistent order
 * to prevent circular wait conditions.
 * This can be achieved by always acquiring locks in the same order
 * (e.g., sort locks before using them).
 */
public class DeadlockExample {
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new DeadlockThread(lock1, lock2), "Thread1");
        Thread thread2 = new Thread(new DeadlockThread(lock2, lock1), "Thread2");

        thread1.start();
        thread2.start();
    }

    private static class DeadlockThread implements Runnable {
        private Object lock1;
        private Object lock2;

        public DeadlockThread(Object lock1, Object lock2) {
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        @Override
        public void run() {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + " acquired lock1");
                try {
                    Thread.sleep(10000); // Simulating some work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + " acquired lock2");
                }
            }
        }
    }
}