package com.hc.sap.anywhere.api.appointmentactivity.bean.relation;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Activity related customers.
 * 
 * @author xuhaijiang
 *
 */
public class ActivityCustomerInfo {
	
	@JsonProperty(value = "id")
	private long id;
	@JsonProperty(value = "customerId")
	private long customerId;
	
	public ActivityCustomerInfo(){}
	
	public ActivityCustomerInfo(long customerId){
		this.customerId = customerId;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	
	
}

