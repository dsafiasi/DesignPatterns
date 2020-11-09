package DIContainer;

public class User {
    private String name;
    private MobilePhone phone;

//(java.lang.String, DIContainer.Iphone)

    public User(String name, MobilePhone phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public MobilePhone getPhone() {
        return phone;
    }
}
