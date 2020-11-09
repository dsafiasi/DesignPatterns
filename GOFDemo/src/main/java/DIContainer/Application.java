package DIContainer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Application {


    //(String id, List<ConstructorArg> constructArgs, String className, boolean isLazyInit, boolean isSingleton) {

    public static void main(String[] args) throws IllegalAccessException, NoSuchBeanDefinitionException, InstantiationException, InvocationTargetException {


        BeanDefinition.ConstructorArg arg1 = new BeanDefinition.ConstructorArg(false, String.class, "cris");
        BeanDefinition.ConstructorArg arg2 = new BeanDefinition.ConstructorArg(true, MobilePhone.class, "iphone");



        List<BeanDefinition.ConstructorArg> argList = new ArrayList<>();
        argList.add(arg1);
        argList.add(arg2);

        BeanDefinition userDefinitionA = new BeanDefinition("user", argList, User.class.getName(), false, true);
        BeanDefinition mobileDefinition = new BeanDefinition("iphone", null, Iphone.class.getName(), false, true);


        List<BeanDefinition> beanDefinitionList = new ArrayList<>();
        beanDefinitionList.add(mobileDefinition);
        beanDefinitionList.add(userDefinitionA);

        BeanFactory factory = new BeanFactory();
        factory.addBeanDefinitions(beanDefinitionList);
        User user = (User) factory.getBean("user");
        System.out.println(user.getName());

//        System.out.println();
//        try {
//            System.out.println(Iphone.class.getName());
//            Class bean = Class.forName(Iphone.class.getName());
//            System.out.println(bean.getInterfaces()[0]);// 兼容不了。
////            System.out.println(bean == null);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }


    }


}
