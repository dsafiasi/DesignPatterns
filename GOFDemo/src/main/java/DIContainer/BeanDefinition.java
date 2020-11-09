package DIContainer;

import java.util.List;

public class BeanDefinition {
    private String id;
    private List<ConstructorArg> constructArgs;
    private String className;

    public String getClassName() {
        return className;
    }


    public BeanDefinition(String id, List<ConstructorArg> constructArgs, String className, boolean isLazyInit, boolean isSingleton) {
        this.id = id;
        this.constructArgs = constructArgs;
        this.className = className;
        this.isLazyInit = isLazyInit;
        this.isSingleton = isSingleton;
    }

    private boolean isLazyInit;
    private boolean isSingleton;


    public boolean isLazyInit() {
        return isLazyInit;
    }

    public boolean isSingleton() {
        return isSingleton;
    }


    public String getId() {
        return id;
    }

    public List<ConstructorArg> getConstructorArgs() {
        return constructArgs;
    }

    //ConstructorArg
    static class ConstructorArg {
        private boolean isRef ;
        private Class type;
        private Object arg;// 具体的值

        public Class getType() {
            return type;
        }

        public Object getArg() {
            return arg;
        }

        public boolean isRef() {
            return isRef;
        }

        public ConstructorArg(boolean isRef, Class type, Object arg) {
            this.isRef = isRef;
            this.type = type;
            this.arg = arg;
        }



    }


}
