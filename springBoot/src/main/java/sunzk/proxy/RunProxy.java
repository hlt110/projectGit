package sunzk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class RunProxy {
    public static void main(String[] args) {
        Person p = new Person();
        InvocationHandler handler = new HireProxy(p);
        Hire h = (Hire) Proxy.newProxyInstance(p.getClass().getClassLoader(),p.getClass().getInterfaces(),handler);
        h.hireHouse();
    }

}
