package com.hc.sap.anywhere.api.customer;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.hc.sap.anywhere.api.common.base.address.AddressInfo;
import com.hc.sap.anywhere.api.common.base.address.CountryCode;
import com.hc.sap.anywhere.api.common.base.address.StateCode;
import com.hc.sap.anywhere.api.common.base.gender.Gender;
import com.hc.sap.anywhere.api.common.base.language.LanguageCode;
import com.hc.sap.anywhere.api.common.base.language.LanguageInfo;
import com.hc.sap.anywhere.api.common.base.user.UserInfo;
import com.hc.sap.anywhere.api.common.query.RequestParams;
import com.hc.sap.anywhere.api.common.query.options.Filter;
import com.hc.sap.anywhere.api.common.query.options.Limit;
import com.hc.sap.anywhere.api.common.query.options.Offset;
import com.hc.sap.anywhere.api.common.query.options.Operator;
import com.hc.sap.anywhere.api.common.query.options.Orderby;
import com.hc.sap.anywhere.api.common.query.options.Select;
import com.hc.sap.anywhere.api.common.response.AnwSimpleResponse;
import com.hc.sap.anywhere.api.common.util.JsonMapper;
import com.hc.sap.anywhere.api.customer.bean.Customer;
import com.hc.sap.anywhere.api.customer.bean.enumeration.CustomerType;
import com.hc.sap.anywhere.api.customer.bean.enumeration.MarketingStatus;
import com.hc.sap.anywhere.api.customer.bean.enumeration.Stage;
import com.hc.sap.anywhere.api.customer.bean.enumeration.Status;
import com.hc.sap.anywhere.api.customer.bean.enumeration.TaxType;
import com.hc.sap.anywhere.api.customer.bean.relation.CustomerAddressLine;
import com.hc.sap.anywhere.api.customer.bean.relation.CustomerPriceListLine;
import com.hc.sap.anywhere.api.customer.bean.relation.MembershipLevelInfo;

public class CustomersTest {

	private Filter filter = new Filter("createdBy.id", Operator.Equals, 36);
	private Select select = new Select("id","title","createdBy");
	private Orderby orderby = new Orderby(Operator.ORDERBY_ASC, "id");
	private Offset offset = new Offset(1);
	private Limit limit = new Limit(5);
	
	
	@Test
	public void getCustomersDefaultTest() {
		
		RequestParams requestParams = getRequestParams();
		AnwSimpleResponse anwSimpleResponse = Customers.defaults().getCustomersDefault(requestParams);
		System.out.println(anwSimpleResponse.getContent());
		List<Customer> appointmentActivityList1 = Customers.defaults().jsonToCustomerList(anwSimpleResponse.getContent());
		
		for (Customer customer : appointmentActivityList1) {
			System.out.println(customer.getId() + "\t" + customer.getTitle() + "\t" + customer.getCreatedBy().getName()+ "\t" + customer.getPosition() );
		}
	}
	
	
	
	
	@Test
	public void createCustomerTest() {
		String customerJson = createJson(4,null);
		
		AnwSimpleResponse anwSimpleResponse = Customers.defaults().postCustomerDefault(customerJson);
		System.out.println(anwSimpleResponse.getId()); //10797
	}
	
	public RequestParams getRequestParams(){
		return new RequestParams()
				//.setFilter(filter)
				//.setSelect(select)
				.setLimit(limit)
				.setOffset(offset)
				.setOrderby(orderby);
	}
	
	@Test
	public void testJsonBean() {
		Customer customer = createBean(2);
		
		String json = JsonMapper.nonDefaultMapper().toJson(customer);
		
		System.out.println(json);
		
		Customer bean = JsonMapper.nonDefaultMapper().fromJson(json, Customer.class);
	
		System.out.println(bean.getCustomerType() 
				+ "\t" +
				bean.getDisplayName()
		);
		
		String json1 = JsonMapper.nonDefaultMapper().toJson(bean);
		System.out.println(json1);
	}
	
	
	@Test
	public void getCustomerByIdDefaultTest() {
		
		long customerId = 10797;
		RequestParams requestParams = getRequestParams();
		AnwSimpleResponse anwSimpleResponse = Customers.defaults().getCustomerByIdDefault(customerId,requestParams.get());
		System.out.println(anwSimpleResponse.getContent());
		Customer customer = JsonMapper.defaultMapper().fromJson(anwSimpleResponse.getContent(), Customer.class);
		
		System.out.println(customer.getId() + "\t" + customer.getFirstName() + "\t" + customer.getTitle() + "\t" + customer.getCreatedBy().getName());
	}
	
	@Test
	public void patchCustomerByIdDefaultTest() {
		
		long customerId = 10797;
		String customerJson = createJson(3,"patch");
		AnwSimpleResponse anwSimpleResponse = Customers.defaults().patchCustomerByIdDefault(customerId,customerJson);
		System.out.println(anwSimpleResponse.getContent());
		Customer customer = JsonMapper.defaultMapper().fromJson(anwSimpleResponse.getContent(), Customer.class);
		
		System.out.println(customer.getId() + "\t" + customer.getFirstName() + "\t" + customer.getTitle() + "\t" + customer.getCreatedBy().getName());
	}
	
	
	
	
	
	
	
	
	
	
	public static String createJson(int num,String pacth){
		Customer customer = createBean(num);
		if(null != pacth){
			String firstName = customer.getFirstName();
			customer = new Customer(); 
			customer.setFirstName(firstName + pacth);
		}
		String json = JsonMapper.nonDefaultMapper().toJson(customer);
		System.out.println(json);
		return json;
	}
	
	public static Customer createBean(int num){
		Customer customer = new Customer();
		customer.setCustomerCode("xhj" + num);
		customer.setCustomerType(CustomerType.INDIVIDUAL_CUSTOMER);
		customer.setCustomerName("customerName" + num);
		customer.setLastName("lastName" + num);
		customer.setFirstName("firstName" + num);
		customer.setDisplayName("displayName" + num);
		customer.setMembershipNo("membershipNo" + num);
		customer.setVatRegistrationNumber("vatRegistrationNumber" + num);
		//customer.setTitle("Mr.");
		//customer.setPosition("position");
		customer.setStage(Stage.CUSTOMER);
		//customer.setMobile("mobile");
		//customer.setPhone("phone");
		//customer.setFax("fax");
		//customer.setEmail("email");
		//customer.setWebSite("web Site");
		customer.setCreatedBy(new UserInfo("xhj"));
		customer.setUpdatedBy(new UserInfo("xhj"));
		customer.setOwner(new UserInfo("xhj"));
		customer.setStatus(Status.ACTIVE);
		customer.setRemark("remark" + num);
		customer.setCreditLimit(5l);
		customer.setCreditLimitThreshold(5l);
		customer.setLanguage(new LanguageInfo(LanguageCode.en_US));
		customer.setGender(Gender.MALE);
		customer.setDateOfBirth(new Date());
		customer.setLastMarketingCampaign(new Date());
		customer.setMarketingStatus(MarketingStatus.SUBSCRIBED);
		customer.setTaxType(TaxType.LIABLE);
		customer.setCreationTime(new Date());
		customer.setUpdateTime(new Date());
		customer.setMembershipEnabled(Boolean.TRUE);
		customer.setMembershipBalance(5l);
		customer.setMembershipLevelInfo(new MembershipLevelInfo("MembershipLevelInfo" + num));
		customer.setCustomerPriceListLines(Lists.newArrayList(new CustomerPriceListLine(1l),new CustomerPriceListLine(2l)));
		
		AddressInfo address = new AddressInfo();
		address.setCountryCode(CountryCode.US);
		address.setStateCode(StateCode.CA);
		address.setState("state");
		
		customer.setCustomerAddressLines(Lists.newArrayList(new CustomerAddressLine(address,true,true)));
		
		return customer;
	}
	
}
