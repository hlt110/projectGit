package daili;

import java.lang.reflect.Proxy;



public class Test {
 public static void main(String[] args) {
	Userr u = new UserrIpml("HELLo");
	Invoke in = new Invoke(u);
	Userr s = (Userr)Proxy.newProxyInstance(
			u.getClass().getClassLoader(), 
			u.getClass().getInterfaces(), 
			in);
	s.getName();
	
}
}
