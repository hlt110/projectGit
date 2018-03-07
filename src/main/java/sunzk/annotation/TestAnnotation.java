package sunzk.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class TestAnnotation {
    public static void main(String[] args) throws Exception{
        Class<TestMethod> t = TestMethod.class;
        Method method = t.getDeclaredMethod("father",new Class[]{String.class});
        TestMethod obj = t.newInstance();
        String name = "";
        if(method.isAnnotationPresent(SunZK.class)){
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation a : annotations) {
                if(a.annotationType().equals(SunZK.class)){
                   name = ((SunZK)a).name();
                }
            }
        }
        System.out.println(name + method.invoke(obj,new Object[]{"爷爷"}));
    }
}
