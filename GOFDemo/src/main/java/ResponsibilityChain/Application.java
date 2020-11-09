package ResponsibilityChain;

public class Application {

    public static void main(String[] args) {
        HandlerChain chain = new HandlerChain();
        HandlerA handlerA = new HandlerA();
        HandlerB handlerB = new HandlerB();
        chain.addHandle(handlerA);
        chain.addHandle(handlerB);
        Message message = new Message();
        chain.handle(message);
        System.out.println(message.getMessage());

    }

}
