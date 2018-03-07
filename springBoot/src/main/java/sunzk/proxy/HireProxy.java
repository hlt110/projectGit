package sunzk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HireProxy implements InvocationHandler{

    private Object obj;


    public HireProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理准备开始");
        Object newObj = method.invoke(obj,args);
        System.out.println("代理准备结束");
        return newObj;
    }
}
