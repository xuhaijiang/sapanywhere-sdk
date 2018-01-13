package com.hc.sap.anywhere.api.customer.bean.relation;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Membership level information.
 * 
 * @author xuhaijiang
 *
 */
public class MembershipLevelInfo {

	@JsonProperty(value = "id")
	private long id;
	@JsonProperty(value = "name")
	private String name;

	public MembershipLevelInfo() {
	}

	public MembershipLevelInfo(String name) {
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
