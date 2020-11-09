package ObserverDemo;

public class Runner implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("wake");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
