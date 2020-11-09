package ResponsibilityChain;

public class HandlerA implements IHandler{
    @Override
    public boolean doHandle(Message message) {
        boolean handled = false;
        if (message.getMesLength() > 0) {
            message.add("handler a");
            handled = true;
        }

        return handled;
    }
}
