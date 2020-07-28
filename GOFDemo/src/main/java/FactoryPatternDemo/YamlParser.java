package FactoryPatternDemo;

public class YamlParser implements Parser{

    @Override
    public Source parser() {
        System.out.println("paser file of yaml now");
        return new Source("yaml");
    }
}
