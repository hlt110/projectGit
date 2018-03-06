package daili;

public class UserrIpml implements Userr {

	private String name;
	public UserrIpml(String name){
		this.name=name;
	}
	@Override
	public String getName() {
		return name;
	}

}
