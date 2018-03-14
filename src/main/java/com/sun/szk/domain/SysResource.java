package com.sun.szk.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.szk.base.core.domain.BaseEntity;




public class SysResource extends BaseEntity{
	
	/** 
	 *    
	*/

	private String resourceid;
	/** 
	 *    
	*/
	private String name;
	/** 
	 *    
	*/
	private String type;
	/** 
	 *    
	*/
	private String url;
	/** 
	 *    
	*/
	private Integer parentid;
	/** 
	 *    
	*/
	private String parentids;
	/** 
	 *    
	*/
	private String permission;
	/** 
	 *    
	*/
	private Integer state;
	/** 
	 *    
	*/
	private String description;
	public String getResourceid() {
	    return this.resourceid;
	}
	public SysResource setResourceid(String resourceid) {
		this.resourceid=resourceid;
		return this;
	}
	public String getName() {
	    return this.name;
	}
	public SysResource setName(String name) {
		this.name=name;
		return this;
	}
	public String getType() {
	    return this.type;
	}
	public SysResource setType(String type) {
		this.type=type;
		return this;
	}
	public String getUrl() {
	    return this.url;
	}
	public SysResource setUrl(String url) {
		this.url=url;
		return this;
	}
	public Integer getParentid() {
	    return this.parentid;
	}
	public SysResource setParentid(Integer parentid) {
		this.parentid=parentid;
		return this;
	}
	public String getParentids() {
	    return this.parentids;
	}
	public SysResource setParentids(String parentids) {
		this.parentids=parentids;
		return this;
	}
	public String getPermission() {
	    return this.permission;
	}
	public SysResource setPermission(String permission) {
		this.permission=permission;
		return this;
	}
	public Integer getState() {
	    return this.state;
	}
	public SysResource setState(Integer state) {
		this.state=state;
		return this;
	}
	public String getDescription() {
	    return this.description;
	}
	public SysResource setDescription(String description) {
		this.description=description;
		return this;
	}
}
