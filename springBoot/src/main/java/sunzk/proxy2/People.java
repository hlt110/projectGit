package sunzk.proxy2;

public class People implements Fruit{
    private String name;
    private String id;
    private String age;

    public People(String name, String id, String age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String eatFruit() {
        System.out.println( name + "shabi");
        return null;
    }
    @Override
    public void hhh(){
        System.out.println( name + "congming");
    }
}
