package mobi.sunfield.business.common.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Invoke implements InvocationHandler{

	public Object obj;
	public Invoke(Object obj){
		this.obj=obj;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("before the function \""+method.getName()+"\""); 
		Object ret = method.invoke(obj, args); 
		System.out.println(ret); 
		System.out.println("after the function \""+method.getName()+"\"");
		return ret;
	}


}
