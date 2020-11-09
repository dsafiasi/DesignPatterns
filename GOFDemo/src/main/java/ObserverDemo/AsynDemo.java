package ObserverDemo;

public class AsynDemo {

    public String asyn(Runnable task) {
        new Thread(task).start();
        return "return true";
    }

    public static void main(String[] args) {
        AsynDemo asynDemo = new AsynDemo();
        String result = asynDemo.asyn(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println("wake");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(result);
    }

}
