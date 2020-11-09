package ProxyDemo;

import java.lang.reflect.Proxy;

public class MyProxyDemo {
    public static void main(String[] args) {
        HelloImpl hello = new HelloImpl();
        MyInvocationHandler handler = new MyInvocationHandler(hello);
        HelloImpl proxyHello = (HelloImpl) Proxy.newProxyInstance(HelloImpl.class.getClassLoader(),
                HelloImpl.class.getInterfaces(),
                handler);

        proxyHello.sayHello();
    }
}
