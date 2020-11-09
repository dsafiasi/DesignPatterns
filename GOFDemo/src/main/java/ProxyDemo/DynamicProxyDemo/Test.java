package ProxyDemo.DynamicProxyDemo;

public class Test {
    public static void main(String[] args) {
        MetricCollectorProxy proxy = new MetricCollectorProxy();
        IService service = (IService) proxy.createProxy(new Service());

        boolean f = service.login("111111","111111");
        System.out.println(f);
    }
}
