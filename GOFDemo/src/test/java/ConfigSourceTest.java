import FactoryPatternDemo.ConfigSource;
import FactoryPatternDemo.Source;

public class ConfigSourceTest {
    public static void main(String[] args) {
        String a = "aaa.xml";
        ConfigSource configSource = new ConfigSource();
        Source source = configSource.load(a);
        System.out.println(source.getType());
    }
}
