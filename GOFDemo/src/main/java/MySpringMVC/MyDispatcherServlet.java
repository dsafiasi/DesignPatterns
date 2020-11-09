package MySpringMVC;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;


/**
 * 自定义DispatcherServlet
 */
public class MyDispatcherServlet extends HttpServlet {
    //模拟 IOC 容器，保存 Controller 实例对象
    private Map<String,Object> iocContainer = new HashMap<String,Object>();
    //保存 handler 映射
    private Map<String,Method> handlerMapping = new HashMap<String,Method>();
    //自定视图解析器
    private MyViewResolver myViewResolver;


    // init 由tomcat替你调用
    @Override
    public void init(ServletConfig config) throws ServletException {

        scanController(config);
        //初始化 handler 映射
        initHandlerMapping();
        //加载视图解析器
        loadViewResolver(config);
        super.init(config);
    }

    private void scanController(ServletConfig config) {
        SAXReader reader = new SAXReader();
        // 解析 springmvc.xml
        try {
            String path = config.getServletContext().getRealPath("")+"\\WEB-INF\\classes\\"+config.getInitParameter("contextConfigLocation");
            Document document = reader.read(path);
            Element root = document.getRootElement();
            Iterator iter = root.elementIterator();

            while(iter.hasNext()){
                Element ele = (Element) iter.next();
                //<context:component-scan base-package="com.southwind.handler"></context:component-scan>
                if(ele.getName().equals("component-scan")){
                    String packageName = ele.attributeValue("base-package");
                    //获取 base-package 包下的所有类名
                    List<String> list = getClassNames(packageName);
                    for(String str : list){
                        Class clazz = Class.forName(str);

                        if (clazz.isAnnotationPresent(MyController.class)) {
                            //获取 Controller 中 MyRequestMapping 注解的 value
                            MyRequestMapping annotation = (MyRequestMapping) clazz.getAnnotation(MyRequestMapping.class);
                            String value = annotation.value().substring(1);
                            //Controller 实例对象存入 iocContainer
                            iocContainer.put(value, clazz.newInstance());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();// 之后修改
        }
    }

    private void loadViewResolver(ServletConfig config) {
        SAXReader reader = new SAXReader();
        try {
            //解析 springmvc.xml
            String path = config.getServletContext().getRealPath("") + "\\WEB-INF\\classes\\" + config.getInitParameter("contextConfigLocation");
            Document document = reader.read(path);
            Element root = document.getRootElement();
            Iterator iter = root.elementIterator();
            //<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
            //    <property name="prefix" value="/"></property>
            //    <property name="suffix" value=".jsp"></property>
            while (iter.hasNext()) {
                Element ele = (Element) iter.next();
                if (ele.getName().equals("bean")) {
                    String className = ele.attributeValue("class");
                    Class clazz = Class.forName(className);
                    Object obj = clazz.newInstance();

                    Method prefixMethod = clazz.getMethod("setPrefix", String.class);
                    Method suffixMethod = clazz.getMethod("setSuffix", String.class);

                    Iterator beanIter = ele.elementIterator();
                    //获取 property 值
                    Map<String, String> propertyMap = new HashMap<String, String>();
                    while (beanIter.hasNext()) {
                        Element beanEle = (Element) beanIter.next();
                        String name = beanEle.attributeValue("name");
                        String value = beanEle.attributeValue("value");
                        propertyMap.put(name, value);
                    }
                    for (String str : propertyMap.keySet()) {
                        if (str.equals("prefix")) {
                            prefixMethod.invoke(obj, propertyMap.get("name"));
                        }
                        if (str.equals("suffix")) {
                            suffixMethod.invoke(obj, propertyMap.get(str));
                        }
                    }
                    myViewResolver = (MyViewResolver) obj;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();// 后续补充
        }
    }

    private void initHandlerMapping() {
        for(String str:iocContainer.keySet()){
            Class clazz = iocContainer.get(str).getClass();
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                //判断方式是否添加 MyRequestMapping 注解
                if(method.isAnnotationPresent(MyRequestMapping.class)){
                    //获取 Method 中 MyRequestMapping 注解的 value
                    MyRequestMapping annotation = method.getAnnotation(MyRequestMapping.class);
                    String value = annotation.value().substring(1);
                    //method 存入 methodMapping
                    handlerMapping.put(value, method);
                }
            }
        }
    }


    // base-package="com.southwind.handler
    // com/southwind/handler

    private List<String> getClassNames(String packageName) {
        List<String> classNameList = new ArrayList<String>();
        String packagePath = packageName.replace(".", "/");
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(packagePath);
        if (url != null) {
            File file = new File(url.getPath());
            File[] childFiles = file.listFiles();
            for(File childFile : childFiles){
                String className = packageName+"."+childFile.getName().replace(".class", "");
                classNameList.add(className);
            }
        }
        return classNameList;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String handlerUri = req.getRequestURI().split("/")[2];

        Object obj = iocContainer.get(handlerUri);

        String methodUri = req.getRequestURI().split("/")[3];
        Method method = handlerMapping.get(methodUri);

        try {
            String value = (String) method.invoke(obj);
            // 视图解析器将逻辑视图转换为物理视图
            String result = myViewResolver.jspMapping(value);
            req.getRequestDispatcher(result).forward(req, resp);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
