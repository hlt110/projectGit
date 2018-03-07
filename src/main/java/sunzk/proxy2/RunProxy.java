package sunzk.proxy2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class RunProxy {
    public static void main(String[] args) {
        People p = new People("苗壮","111","18");
        InvocationHandler i = new EatProxy(p);
        Fruit f = (Fruit) Proxy.newProxyInstance(p.getClass().getClassLoader(),p.getClass().getInterfaces(),i);
        f.hhh();
    }
}
