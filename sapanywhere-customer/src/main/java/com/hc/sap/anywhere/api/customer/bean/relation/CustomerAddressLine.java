package com.hc.sap.anywhere.api.customer.bean.relation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hc.sap.anywhere.api.common.base.address.AddressInfo;

/**
 * The detail information of address
 * 
 * @author xuhaijiang
 *
 */
public class CustomerAddressLine {

	@JsonProperty(value = "id")
	private long id;
	@JsonProperty(value = "address")
	private AddressInfo address;

	/**
	 * Whether is the default billing address of the customer.
	 */
	@JsonProperty(value = "defaultBillingTo")
	private boolean defaultBillingTo;

	/**
	 * Whether is the default shipping address of the customer.
	 */
	@JsonProperty(value = "defaultShippingTo")
	private boolean defaultShippingTo;

	public CustomerAddressLine() {
	}

	public CustomerAddressLine(AddressInfo address, boolean defaultBillingTo, boolean defaultShippingTo) {
		this.address = address;
		this.defaultBillingTo = defaultBillingTo;
		this.defaultShippingTo = defaultShippingTo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AddressInfo getAddress() {
		return address;
	}

	public void setAddress(AddressInfo address) {
		this.address = address;
	}

	public boolean isDefaultBillingTo() {
		return defaultBillingTo;
	}

	public void setDefaultBillingTo(boolean defaultBillingTo) {
		this.defaultBillingTo = defaultBillingTo;
	}

	public boolean isDefaultShippingTo() {
		return defaultShippingTo;
	}

	public void setDefaultShippingTo(boolean defaultShippingTo) {
		this.defaultShippingTo = defaultShippingTo;
	}

}
