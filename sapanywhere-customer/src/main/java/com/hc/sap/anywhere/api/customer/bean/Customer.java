package com.hc.sap.anywhere.api.customer.bean;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.hc.sap.anywhere.api.common.base.gender.Gender;
import com.hc.sap.anywhere.api.common.base.language.LanguageInfo;
import com.hc.sap.anywhere.api.common.base.user.UserInfo;
import com.hc.sap.anywhere.api.customer.bean.enumeration.CustomerType;
import com.hc.sap.anywhere.api.customer.bean.enumeration.MarketingStatus;
import com.hc.sap.anywhere.api.customer.bean.enumeration.Stage;
import com.hc.sap.anywhere.api.customer.bean.enumeration.Status;
import com.hc.sap.anywhere.api.customer.bean.enumeration.TaxType;
import com.hc.sap.anywhere.api.customer.bean.relation.CustomerAddressLine;
import com.hc.sap.anywhere.api.customer.bean.relation.CustomerPriceListLine;
import com.hc.sap.anywhere.api.customer.bean.relation.MembershipLevelInfo;

/**
 * Customer Master Data.
 * 
 * @author xuhaijiang
 *
 */
public class Customer {

	@JsonProperty(value = "customFields")
	private Object customFields;
	
	/**
	 * readonly
	 */
	@JsonProperty(value = "id" , access = Access.WRITE_ONLY)
	private long id;

	/**
	 * Unique code for the customer.
	 */
	@JsonProperty(value = "customerCode")
	private String customerCode;

	/**
	 * required
	 */
	@JsonProperty(value = "customerType", required = true)
	private CustomerType customerType;

	/**
	 * Corporate name of the customer.
	 */
	@JsonProperty(value = "customerName")
	private String customerName;

	@JsonProperty(value = "lastName")
	private String lastName;
	@JsonProperty(value = "firstName")
	private String firstName;

	/**
	 * readonly
	 */
	@JsonProperty(value = "displayName", access = Access.WRITE_ONLY)
	private String displayName;
	
	@JsonProperty(value = "membershipNo")
	private String membershipNo;
	@JsonProperty(value = "vatRegistrationNumber")
	private String vatRegistrationNumber;

	/**
	 * valid values
	 * Only used for individual type customer. example: "Mr."
	 */
	@JsonProperty(value = "title")
	private String title;

	/**
	 * valid values
	 * Only used for individual type customer.
	 */
	@JsonProperty(value = "position")
	private String position;

	/**
	 * Main contact person for the customer. Only used for corporate type
	 * customer.
	 */
	@JsonProperty(value = "mainContactId")
	private String mainContactId;

	/**
	 * required
	 */
	@JsonProperty(value = "stage", required = true)
	private Stage stage;

	@JsonProperty(value = "mobile")
	private String mobile;
	@JsonProperty(value = "phone")
	private String phone;
	@JsonProperty(value = "fax")
	private String fax;
	@JsonProperty(value = "email")
	private String email;

	@JsonProperty(value = "webSite")
	private String webSite;
	
	/**
	 * readonly
	 */
	@JsonProperty(value = "createdBy", access = Access.WRITE_ONLY)
	private UserInfo createdBy;
	
	/**
	 * readonly
	 */
	@JsonProperty(value = "updatedBy", access = Access.WRITE_ONLY)
	private UserInfo updatedBy;
	
	/**
	 * readonly
	 */
	@JsonProperty(value = "owner", access = Access.WRITE_ONLY)
	private UserInfo owner;
	
	/**
	 * required
	 */
	@JsonProperty(value = "status", required = true)
	private Status status;

	@JsonProperty(value = "remark")
	private String remark;
	@JsonProperty(value = "creditLimit")
	private long creditLimit;
	@JsonProperty(value = "creditLimitThreshold")
	private long creditLimitThreshold;
	@JsonProperty(value = "language")
	private LanguageInfo language;
	@JsonProperty(value = "gender")
	private Gender gender;

	/**
	 * string (date-time)
	 * Birthday of the customer. Only used for individual type customer.
	 */
	@JsonProperty(value = "dateOfBirth")
	private Date dateOfBirth;

	/**
	 * string (date-time)
	 * Date of last marketing campaign.
	 */
	@JsonProperty(value = "lastMarketingCampaign")
	private Date lastMarketingCampaign;
	
	/**
	 * required
	 */
	@JsonProperty(value = "marketingStatus", required = true)
	private MarketingStatus marketingStatus;
	
	@JsonProperty(value = "taxType")
	private TaxType taxType;

	/**
	 * readonly
	 */
	@JsonProperty(value = "creationTime", access = Access.WRITE_ONLY)
	private Date creationTime;
	
	/**
	 * readonly
	 */
	@JsonProperty(value = "updateTime" , access = Access.WRITE_ONLY)
	private Date updateTime;
	
	/**
	 * readonly
	 */
	@JsonProperty(value = "membershipEnabled", access = Access.WRITE_ONLY)
	private boolean membershipEnabled;
	
	/**
	 * readonly
	 */
	@JsonProperty(value = "membershipBalance", access = Access.WRITE_ONLY)
	private long membershipBalance;
	
	/**
	 * readonly
	 */
	@JsonProperty(value = "membershipLevelInfo", access = Access.WRITE_ONLY)
	private MembershipLevelInfo membershipLevelInfo;
	
	@JsonProperty(value = "customerPriceListLines")
	private List<CustomerPriceListLine> customerPriceListLines;
	
	/**
	 * readonly
	 */
	@JsonProperty(value = "customerAddressLines", access = Access.WRITE_ONLY)
	private List<CustomerAddressLine> customerAddressLines;

	public Object getCustomFields() {
		return customFields;
	}

	public void setCustomFields(Object customFields) {
		this.customFields = customFields;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getMembershipNo() {
		return membershipNo;
	}

	public void setMembershipNo(String membershipNo) {
		this.membershipNo = membershipNo;
	}

	public String getVatRegistrationNumber() {
		return vatRegistrationNumber;
	}

	public void setVatRegistrationNumber(String vatRegistrationNumber) {
		this.vatRegistrationNumber = vatRegistrationNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMainContactId() {
		return mainContactId;
	}

	public void setMainContactId(String mainContactId) {
		this.mainContactId = mainContactId;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public UserInfo getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserInfo createdBy) {
		this.createdBy = createdBy;
	}

	public UserInfo getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(UserInfo updatedBy) {
		this.updatedBy = updatedBy;
	}

	public UserInfo getOwner() {
		return owner;
	}

	public void setOwner(UserInfo owner) {
		this.owner = owner;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public long getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(long creditLimit) {
		this.creditLimit = creditLimit;
	}

	public long getCreditLimitThreshold() {
		return creditLimitThreshold;
	}

	public void setCreditLimitThreshold(long creditLimitThreshold) {
		this.creditLimitThreshold = creditLimitThreshold;
	}

	public LanguageInfo getLanguage() {
		return language;
	}

	public void setLanguage(LanguageInfo language) {
		this.language = language;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getLastMarketingCampaign() {
		return lastMarketingCampaign;
	}

	public void setLastMarketingCampaign(Date lastMarketingCampaign) {
		this.lastMarketingCampaign = lastMarketingCampaign;
	}

	public MarketingStatus getMarketingStatus() {
		return marketingStatus;
	}

	public void setMarketingStatus(MarketingStatus marketingStatus) {
		this.marketingStatus = marketingStatus;
	}

	public TaxType getTaxType() {
		return taxType;
	}

	public void setTaxType(TaxType taxType) {
		this.taxType = taxType;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public boolean isMembershipEnabled() {
		return membershipEnabled;
	}

	public void setMembershipEnabled(boolean membershipEnabled) {
		this.membershipEnabled = membershipEnabled;
	}

	public long getMembershipBalance() {
		return membershipBalance;
	}

	public void setMembershipBalance(long membershipBalance) {
		this.membershipBalance = membershipBalance;
	}

	public MembershipLevelInfo getMembershipLevelInfo() {
		return membershipLevelInfo;
	}

	public void setMembershipLevelInfo(MembershipLevelInfo membershipLevelInfo) {
		this.membershipLevelInfo = membershipLevelInfo;
	}

	public List<CustomerPriceListLine> getCustomerPriceListLines() {
		return customerPriceListLines;
	}

	public void setCustomerPriceListLines(List<CustomerPriceListLine> customerPriceListLines) {
		this.customerPriceListLines = customerPriceListLines;
	}

	public List<CustomerAddressLine> getCustomerAddressLines() {
		return customerAddressLines;
	}

	public void setCustomerAddressLines(List<CustomerAddressLine> customerAddressLines) {
		this.customerAddressLines = customerAddressLines;
	}

}
