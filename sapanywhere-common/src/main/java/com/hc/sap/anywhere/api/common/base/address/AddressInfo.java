package com.hc.sap.anywhere.api.common.base.address;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * address Model
 * 
 * @author xuhaijiang
 *
 */
public class AddressInfo {

	@JsonProperty(value = "countryCode")
	private CountryCode countryCode;
	@JsonProperty(value = "stateCode")
	private StateCode stateCode;

	/**
	 * State description. Please provide the state information here if the state
	 * is not in US, CN and GB and leave the state code field as null.
	 */
	@JsonProperty(value = "state")
	private String state;

	@JsonProperty(value = "cityName")
	private String cityName;

	/**
	 * Main street description.
	 */
	@JsonProperty(value = "street1")
	private String street1;

	/**
	 * Concrete location description.
	 */
	@JsonProperty(value = "street2")
	private String street2;
	@JsonProperty(value = "zipCode")
	private String zipCode;
	@JsonProperty(value = "mobile")
	private String mobile;
	@JsonProperty(value = "telephone")
	private String telephone;
	@JsonProperty(value = "recipientName")
	private String recipientName;
	@JsonProperty(value = "displayName")
	private String displayName;
	@JsonProperty(value = "addressType")
	private String addressType;

	public AddressInfo() {
	}

	public CountryCode getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(CountryCode countryCode) {
		this.countryCode = countryCode;
	}

	public StateCode getStateCode() {
		return stateCode;
	}

	public void setStateCode(StateCode stateCode) {
		this.stateCode = stateCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

}
