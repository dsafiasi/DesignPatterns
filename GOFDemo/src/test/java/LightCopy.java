import java.util.*;

class Test {
    private String name;

    public Test(String name) {
        this.name = name;
    }
}

public class LightCopy {


    public static void main(String[] args) {
        Test test = new Test("aaa");
        System.out.println(test.getClass().getClassLoader().getClass().getName());

    }
}
