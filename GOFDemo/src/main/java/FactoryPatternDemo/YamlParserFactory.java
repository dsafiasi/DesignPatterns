package FactoryPatternDemo;

public class YamlParserFactory implements ParserFactory{
    @Override
    public Parser creatPaerser() {
        return new YamlParser();
    }
}
