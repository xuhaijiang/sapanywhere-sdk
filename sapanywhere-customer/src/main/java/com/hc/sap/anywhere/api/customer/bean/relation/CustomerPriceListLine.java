package com.hc.sap.anywhere.api.customer.bean.relation;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Customer price list.
 * 
 * @author xuhaijiang
 *
 */
public class CustomerPriceListLine {
	
	@JsonProperty(value = "customFields")
	private Object customFields;
	
	@JsonProperty(value = "id")
	private long id;
	@JsonProperty(value = "priceListId")
	private long priceListId;

	public CustomerPriceListLine() {
	}

	public CustomerPriceListLine(long priceListId) {
		this.priceListId = priceListId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPriceListId() {
		return priceListId;
	}

	public void setPriceListId(long priceListId) {
		this.priceListId = priceListId;
	}

	public Object getCustomFields() {
		return customFields;
	}

	public void setCustomFields(Object customFields) {
		this.customFields = customFields;
	}
	
}
