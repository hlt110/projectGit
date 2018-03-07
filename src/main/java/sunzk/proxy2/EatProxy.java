package sunzk.proxy2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class EatProxy implements InvocationHandler{
    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public EatProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理开始");
        Object o = method.invoke(object,args);
        System.out.println("代理结束");
        return o;
    }
}
