package ProxyDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 加入通用的逻辑(对所有方法皆适用),此处简单打印
        System.out.println("hello world");
        Object result = method.invoke(target, args);
        return result;
    }
}
