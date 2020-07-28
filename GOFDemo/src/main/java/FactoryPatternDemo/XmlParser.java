package FactoryPatternDemo;

public class XmlParser implements Parser{


    @Override
    public Source parser() {
        System.out.println("parser file of xml now");
        return new Source("xml");
    }
}
