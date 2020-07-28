package FactoryPatternDemo;

public class ConfigSource {
    public Source load(String filePath) {
        String fileExtention = getFileExtention(filePath);
        ParserFactory parserFactory = ParserFactoryMap.getParserFactory("xml");
        if (parserFactory == null) {
            System.out.println("can't supported"+filePath+"extention is"+fileExtention);
        }
        Parser parser = parserFactory.creatPaerser();
        return parser.parser();
    }

    String file1 = "a.xml";
    String file2 = "a.yaml";
    public String getFileExtention(String filePath) {
        String[] units = filePath.split("\\.");
        return units[1];
    }
}
