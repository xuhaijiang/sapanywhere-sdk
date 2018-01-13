package com.hc.sap.anywhere.api.customer;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hc.sap.anywhere.api.common.AnwServiceCall;
import com.hc.sap.anywhere.api.common.BaseApi;
import com.hc.sap.anywhere.api.common.HttpClientFactory;
import com.hc.sap.anywhere.api.common.base.AppSetting;
import com.hc.sap.anywhere.api.common.base.Endpoint;
import com.hc.sap.anywhere.api.common.query.RequestParams;
import com.hc.sap.anywhere.api.common.response.AnwSimpleResponse;
import com.hc.sap.anywhere.api.common.util.JsonMapper;
import com.hc.sap.anywhere.api.customer.bean.Customer;

public class Customers extends BaseApi{
	private static Logger logger = LoggerFactory.getLogger(Customers.class);

	public static Customers defaults() {
		return with(AppSetting.defaultSettings());
	}

	public static Customers with(AppSetting appSetting) {
		Customers customers = new Customers();
		customers.setHttpClient(HttpClientFactory.getInstance().with(appSetting));
		return customers;
	}

	/**
	 * Get a list of customers with paging (optional).
	 * 
	 * @param queryOptions
	 * @return
	 */
	public AnwSimpleResponse getCustomersDefault(String queryOptions) {
		String url = String.format(Endpoint.get("url.customers.get"), queryOptions);
		return AnwServiceCall.get(httpClient, url);
	}

	/**
	 * Create a customer using the given data.
	 * 
	 * @param customerJson
	 * @return The id of the newly created customer.
	 */
	public AnwSimpleResponse postCustomerDefault(String customerJson) {
		String url = Endpoint.get("url.customers.post");
		return AnwServiceCall.post(httpClient, url, customerJson);
	}

	public AnwSimpleResponse getCustomersDefault(RequestParams requestParams) {
		return getCustomersDefault(requestParams.get());
	}

	public List<Customer> getCustomers(String queryOptions) {
		AnwSimpleResponse anwSimpleResponse = getCustomersDefault(queryOptions);
		return jsonToCustomerList(anwSimpleResponse.getContent());
	}

	public List<Customer> getCustomers(RequestParams requestParams) {
		AnwSimpleResponse anwSimpleResponse = getCustomersDefault(requestParams);
		return jsonToCustomerList(anwSimpleResponse.getContent());
	}

	public List<Customer> jsonToCustomerList(String customerJsons) {
		return JsonMapper.defaultMapper().fromJsons(customerJsons, Customer.class);
	}

	/**
	 * Get a customer by id
	 * 
	 * @param customerId
	 * @param queryOptions
	 *            expand or select
	 * @return
	 */
	public AnwSimpleResponse getCustomerByIdDefault(long customerId, String queryOptions) {
		Objects.requireNonNull(customerId, "Customer id required");
		String url = String.format(Endpoint.get("url.customers.by.id.get"), customerId, queryOptions);
		return AnwServiceCall.get(httpClient, url);
	}
	
	/**
	 * Update a customer using the given data.
	 * 
	 * @param customerId
	 * @param customerJsonData
	 * @return 200	void Update successfully
	 */
	public AnwSimpleResponse patchCustomerByIdDefault(long customerId, String customerJsonData) {
		Objects.requireNonNull(customerId, "customer id is required");
		String url = String.format(Endpoint.get("url.customers.by.id.patch"), customerId);
		return AnwServiceCall.patch(httpClient, url, customerJsonData);
	}
}
