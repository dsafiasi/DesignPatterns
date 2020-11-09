package ResponsibilityChain;

public class HandlerB implements IHandler {
    @Override
    public boolean doHandle(Message message) {
        boolean handled = false;
        if (message.getMesLength() <= 20) {
            message.add("handler b");
            handled = true;
        }

        return handled;
    }
}
