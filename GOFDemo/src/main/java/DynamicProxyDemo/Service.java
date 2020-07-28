package DynamicProxyDemo;

public class Service implements IService{
    /**
     * 业务逻辑
     */
    public boolean login(String userName, String passWord) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if ((userName == null || userName.length() < 6 )
                || (passWord == null || passWord.length() < 6)) {
            return false;
        }
        else return true;
    }

}
