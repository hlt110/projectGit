package com.sun.szk.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.szk.base.core.domain.BaseEntity;



@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
@javax.persistence.Table(name = "sys_user")
public class SysUser extends BaseEntity{
	
	/** 
	 *  用户id  
	*/
	@javax.persistence.Id
	private String userid;
	/** 
	 *  用户名  
	*/
	private String username;
	/** 
	 *  密码  
	*/
	private String password;
	/** 
	 *  组织机构ID  
	*/
	private String organizationid;
	/** 
	 *  角色ID  
	*/
	private String roleids;
	/** 
	 *  状态，0,：停用，1启用  
	*/
	private Integer status;
	/** 
	 *  描述  
	*/
	private String description;
	/** 
	 *    
	*/
	private String mC;
	/** 
	 *    
	*/
	private java.util.Date cJSJ;
	/** 
	 *    
	*/
	private java.util.Date xGSJ;
	/** 
	 *    
	*/
	private String yHLX;
	/** 
	 *    
	*/
	private String uPDATEVER;
	/** 
	 *    
	*/
	private String yHEMAIL;
	/** 
	 *  手机  
	*/
	private String phone;
	/** 
	 *  邮箱  
	*/
	private String mailbox;
	/** 
	 *  是否零售商，0:否，1：是  
	*/
	private String ifRetailer;
	/** 
	 *  是否批发商，0:否，1：是  
	*/
	private String ifWholesaler;
	/** 
	 *  是否代理商，0:否，1：是  
	*/
	private String ifAgent;
	/** 
	 *  是否龙平台管理，0:否，1：是  
	*/
	private String ifManagement;
	/** 
	 *  推广人id  
	*/
	private String tgOrgid;
	/** 
	 *  龙币数  
	*/
	private Double lbs;
	/** 
	 *  头像地址  
	*/
	private String yhTx;
	/** 
	 *  工号  
	*/
	private String gh;
	/** 
	 *  推广人工号  
	*/
	private String tgrgh;
	/** 
	 *  创建时间  
	*/
	private java.util.Date createTime;
	/** 
	 *  修改时间  
	*/
	private java.util.Date updateTime;
	public String getUserid() {
	    return this.userid;
	}
	public SysUser setUserid(String userid) {
		this.userid=userid;
		return this;
	}
	public String getUsername() {
	    return this.username;
	}
	public SysUser setUsername(String username) {
		this.username=username;
		return this;
	}
	public String getPassword() {
	    return this.password;
	}
	public SysUser setPassword(String password) {
		this.password=password;
		return this;
	}
	public String getOrganizationid() {
	    return this.organizationid;
	}
	public SysUser setOrganizationid(String organizationid) {
		this.organizationid=organizationid;
		return this;
	}
	public String getRoleids() {
	    return this.roleids;
	}
	public SysUser setRoleids(String roleids) {
		this.roleids=roleids;
		return this;
	}
	public Integer getStatus() {
	    return this.status;
	}
	public SysUser setStatus(Integer status) {
		this.status=status;
		return this;
	}
	public String getDescription() {
	    return this.description;
	}
	public SysUser setDescription(String description) {
		this.description=description;
		return this;
	}
	public String getMC() {
	    return this.mC;
	}
	public SysUser setMC(String mC) {
		this.mC=mC;
		return this;
	}
	public java.util.Date getCJSJ() {
	    return this.cJSJ;
	}
	public SysUser setCJSJ(java.util.Date cJSJ) {
		this.cJSJ=cJSJ;
		return this;
	}
	public java.util.Date getXGSJ() {
	    return this.xGSJ;
	}
	public SysUser setXGSJ(java.util.Date xGSJ) {
		this.xGSJ=xGSJ;
		return this;
	}
	public String getYHLX() {
	    return this.yHLX;
	}
	public SysUser setYHLX(String yHLX) {
		this.yHLX=yHLX;
		return this;
	}
	public String getUPDATEVER() {
	    return this.uPDATEVER;
	}
	public SysUser setUPDATEVER(String uPDATEVER) {
		this.uPDATEVER=uPDATEVER;
		return this;
	}
	public String getYHEMAIL() {
	    return this.yHEMAIL;
	}
	public SysUser setYHEMAIL(String yHEMAIL) {
		this.yHEMAIL=yHEMAIL;
		return this;
	}
	public String getPhone() {
	    return this.phone;
	}
	public SysUser setPhone(String phone) {
		this.phone=phone;
		return this;
	}
	public String getMailbox() {
	    return this.mailbox;
	}
	public SysUser setMailbox(String mailbox) {
		this.mailbox=mailbox;
		return this;
	}
	public String getIfRetailer() {
	    return this.ifRetailer;
	}
	public SysUser setIfRetailer(String ifRetailer) {
		this.ifRetailer=ifRetailer;
		return this;
	}
	public String getIfWholesaler() {
	    return this.ifWholesaler;
	}
	public SysUser setIfWholesaler(String ifWholesaler) {
		this.ifWholesaler=ifWholesaler;
		return this;
	}
	public String getIfAgent() {
	    return this.ifAgent;
	}
	public SysUser setIfAgent(String ifAgent) {
		this.ifAgent=ifAgent;
		return this;
	}
	public String getIfManagement() {
	    return this.ifManagement;
	}
	public SysUser setIfManagement(String ifManagement) {
		this.ifManagement=ifManagement;
		return this;
	}
	public String getTgOrgid() {
	    return this.tgOrgid;
	}
	public SysUser setTgOrgid(String tgOrgid) {
		this.tgOrgid=tgOrgid;
		return this;
	}
	public Double getLbs() {
	    return this.lbs;
	}
	public SysUser setLbs(Double lbs) {
		this.lbs=lbs;
		return this;
	}
	public String getYhTx() {
	    return this.yhTx;
	}
	public SysUser setYhTx(String yhTx) {
		this.yhTx=yhTx;
		return this;
	}
	public String getGh() {
	    return this.gh;
	}
	public SysUser setGh(String gh) {
		this.gh=gh;
		return this;
	}
	public String getTgrgh() {
	    return this.tgrgh;
	}
	public SysUser setTgrgh(String tgrgh) {
		this.tgrgh=tgrgh;
		return this;
	}
	public java.util.Date getCreateTime() {
	    return this.createTime;
	}
	public SysUser setCreateTime(java.util.Date createTime) {
		this.createTime=createTime;
		return this;
	}
	public java.util.Date getUpdateTime() {
	    return this.updateTime;
	}
	public SysUser setUpdateTime(java.util.Date updateTime) {
		this.updateTime=updateTime;
		return this;
	}
}
