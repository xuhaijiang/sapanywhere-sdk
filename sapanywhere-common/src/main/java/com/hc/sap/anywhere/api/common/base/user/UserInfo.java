package com.hc.sap.anywhere.api.common.base.user;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User information.
 * @author xuhaijiang
 *
 */
public class UserInfo {
	/**
	 * User id.
	 */
	@JsonProperty(value = "id")
    private long id;
	
	/**
	 * User name.
	 */
	@JsonProperty(value = "name")
    private String name;
	
	public UserInfo(){}
	
	public UserInfo(long id,String name){
		this.id = id;
		this.name = name;
	}
	
	public UserInfo(String name){
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
