package com.sun.szk.ssm.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;



@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
@javax.persistence.Table(name = "user")
public class User {
	
	/** 
	 *    
	*/
	@javax.persistence.Id
	private Integer id;
	/** 
	 *    
	*/
	private String username;
	/** 
	 *    
	*/
	private String password;
	/** 
	 *    
	*/
	private java.util.Date birthday;
	/** 
	 *    
	*/
	private String sex;
	/** 
	 *    
	*/
	private String address;
	public Integer getId() {
	    return this.id;
	}
	public User setId(Integer id) {
		this.id=id;
		return this;
	}
	public String getUsername() {
	    return this.username;
	}
	public User setUsername(String username) {
		this.username=username;
		return this;
	}
	public String getPassword() {
	    return this.password;
	}
	public User setPassword(String password) {
		this.password=password;
		return this;
	}
	public java.util.Date getBirthday() {
	    return this.birthday;
	}
	public User setBirthday(java.util.Date birthday) {
		this.birthday=birthday;
		return this;
	}
	public String getSex() {
	    return this.sex;
	}
	public User setSex(String sex) {
		this.sex=sex;
		return this;
	}
	public String getAddress() {
	    return this.address;
	}
	public User setAddress(String address) {
		this.address=address;
		return this;
	}
}
