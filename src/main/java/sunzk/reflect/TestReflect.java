package sunzk.reflect;

import sunzk.entity.Employee;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestReflect {

    public static void test() throws Exception{
        Class<?> clazz = Class.forName("sunzk.entity.Employee");
        Employee e = new Employee();
        Class<?> clazz1 = e.getClass();
        Class<Employee> clazz2 = Employee.class;
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(m);
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            System.out.println(f);
        }
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor c : constructors) {
            System.out.println(c);
        }
    }
    public static Class<?> add(Object obj){
       return  obj.getClass();
    }

    public static void test2() throws Exception{
        Class<?> clazz = Class.forName("sunzk.entity.Employee");
        Method method = clazz.getDeclaredMethod("getName",new Class[]{});
        Method method1 = clazz.getDeclaredMethod("setName", new Class[]{String.class});
        System.out.println(method);
        System.out.println(method1);

        Field field = clazz.getDeclaredField("name");
        System.out.println(field);

        Constructor constructor = clazz.getDeclaredConstructor(new Class[]{String.class,Integer.class,String.class});
        System.out.println(constructor);
    }

    public static void test3() throws Exception{
        Class<?> c = Class.forName("sunzk.entity.Employee");
        Constructor constructor = c.getDeclaredConstructor(new Class[]{});
        Object in = constructor.newInstance(new Object[]{});
        System.out.println(in);

        Method[] methods = c.getDeclaredMethods();
        for (Method m : methods) {
           String name = m.getName();
           Boolean b = name.startsWith("set");
           if(b){
               name = name.substring(3);
               name = name.substring(0,1).toLowerCase() + name.substring(1);
               Field field = c.getDeclaredField(name);
               if(field.getType().equals(Integer.class)){
                    m.invoke(in,new Object[]{1});
               }
               if(field.getType().equals(String.class) && "name".equals(name)){
                   m.invoke(in,new Object[]{"王正北"});
               }
               if(field.getType().equals(String.class) && "phone".equals(name)){
                   m.invoke(in,new Object[]{"189546545646"});
               }
           }
        }
        System.out.println(in);

//        Constructor constructor1 = c.getDeclaredConstructor(new Class[]{String.class,Integer.class,String.class});
//        Object in1 = constructor1.newInstance(new Object[]{"苗壮",18,"大连"});
//        System.out.println(in1);
    }

    public static void test5() throws Exception{
        Employee e = new Employee();
        e.setName("含雷霆");
        e.setAge(18);
        e.setPhone("1388888888");
        Object o = copyObj1(e);
        System.out.println(o);

    }
    public static Object copyObj(Object obj) throws Exception{
        Class<?> c = obj.getClass();
        Constructor constructor = c.getDeclaredConstructor(new Class[]{});
        Object instance = constructor.newInstance(new Object[]{});
        Field[] field = c.getDeclaredFields();
        for (Field f : field) {
            String fieldName = f.getName();
            Method gMethod = c.getMethod("get"+fieldName.substring(0,1).toUpperCase() + fieldName.substring(1),new Class[]{});

            Object gValue = gMethod.invoke(obj,new Object[]{});

            Method sMethon = c.getMethod("set"+fieldName.substring(0,1).toUpperCase() + fieldName.substring(1),new Class[]{gMethod.getReturnType()});
            sMethon.invoke(instance,new Object[]{gValue});
        }
        return instance;
    }

    public static void main(String[] args) throws Exception{
        test5();
    }
    public static Object copyObj1(Object obj) throws Exception{
        Class<?> c = obj.getClass();
        Object o = c.newInstance();
        Field[] f = c.getDeclaredFields();
        Method[] m = c.getDeclaredMethods();
        for (Field f1: f) {
            String fieldName = f1.getName();

            fieldName = fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
            Method setM = c.getDeclaredMethod("set"+fieldName,f1.getType());
            Method getM = c.getDeclaredMethod("get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1),new Class[]{});
            setM.invoke(o,getM.invoke(obj,new Object[]{}));
        }
        return o;
    }
}
