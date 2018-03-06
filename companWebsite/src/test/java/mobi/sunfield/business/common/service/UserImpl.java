package mobi.sunfield.business.common.service;

public class UserImpl implements IUser{

	private String name;
	
	public UserImpl(String name){
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
}
