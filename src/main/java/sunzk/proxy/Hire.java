package sunzk.proxy;

public interface Hire {
    public void hireHouse();

    default void aaa(){
        System.out.println("大傻逼");
    }

    static void bbb(){
        System.out.println("超级大傻逼");
    }
}
