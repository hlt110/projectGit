package daili;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;



public class Invoke implements InvocationHandler{
	private Object obj;
	public Invoke(Object obj){
	this.obj=obj;	
	}
	@Override
	public Object invoke(Object proxy, Method mothed, Object[] args)
			throws Throwable {
		System.out.println("start");
		Object re =  mothed.invoke(obj, args);
		System.out.println(obj);
		System.out.println("end");
		return re;
	}

}
