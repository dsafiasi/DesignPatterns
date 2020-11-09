package ResponsibilityChain;

import javax.servlet.Servlet;
import java.util.ArrayList;
import java.util.List;

public class HandlerChain {
    private List<IHandler> handlers = new ArrayList<>();
//    private Servlet servlet;

//    public HandlerChain(Servlet servlet) {
//        this.servlet = servlet;
//    }

    public void addHandle(IHandler handler) {
        handlers.add(handler);
    }

    public void handle(Message message) {
        if (handlers.size() == 0) return;
        for (IHandler handler : handlers) {
            boolean handled = handler.doHandle(message);
            if (handled) {
                System.out.println("invoke servlet.service()");
//                servlet.service();
                break;
            }
        }
    }

}
