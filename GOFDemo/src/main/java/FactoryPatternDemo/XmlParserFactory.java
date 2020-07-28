package FactoryPatternDemo;

public class XmlParserFactory implements ParserFactory{
    @Override
    public Parser creatPaerser() {
        return new XmlParser();
    }
}
