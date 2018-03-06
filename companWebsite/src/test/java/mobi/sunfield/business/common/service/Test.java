package mobi.sunfield.business.common.service;

import java.lang.reflect.Proxy;

public class Test {
	//http://www.shangxueba.com/jingyan/1853835.html
	//http://www.iteye.com/problems/14790
	public static void main(String[] args) {
		IUser user = new UserImpl("HELLO");
		Invoke in = new Invoke(user);
		IUser por = (IUser)Proxy.newProxyInstance(
				user.getClass().getClassLoader(), user.getClass().getInterfaces(),in);
	
		por.getName();
	}
}
