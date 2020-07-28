package FactoryPatternDemo;

import java.util.HashMap;
import java.util.Map;

public class ParserFactoryMap {
    private static final Map<String,ParserFactory> cachedFactories = new HashMap<>();
    static {
        cachedFactories.put("xml",new XmlParserFactory());
        cachedFactories.put("yaml",new YamlParserFactory());
    }
    public static ParserFactory getParserFactory(String type) {
        if (type.isEmpty() || type == null) {
            return null;
        }
        ParserFactory parserFactory = cachedFactories.get(type);
        return parserFactory;
    }


}
