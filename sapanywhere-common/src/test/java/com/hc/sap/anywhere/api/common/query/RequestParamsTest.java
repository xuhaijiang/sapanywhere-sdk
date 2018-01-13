package com.hc.sap.anywhere.api.common.query;

import org.junit.Before;
import org.junit.Test;

import com.hc.sap.anywhere.api.common.query.Options;
import com.hc.sap.anywhere.api.common.query.RequestParams;
import com.hc.sap.anywhere.api.common.query.options.Expand;
import com.hc.sap.anywhere.api.common.query.options.ExpandTest;
import com.hc.sap.anywhere.api.common.query.options.Filter;
import com.hc.sap.anywhere.api.common.query.options.FilterTest;
import com.hc.sap.anywhere.api.common.query.options.Limit;
import com.hc.sap.anywhere.api.common.query.options.LimitTest;
import com.hc.sap.anywhere.api.common.query.options.Offset;
import com.hc.sap.anywhere.api.common.query.options.OffsetTest;
import com.hc.sap.anywhere.api.common.query.options.Operator;
import com.hc.sap.anywhere.api.common.query.options.Orderby;
import com.hc.sap.anywhere.api.common.query.options.OrderbyTest;
import com.hc.sap.anywhere.api.common.query.options.Select;
import com.hc.sap.anywhere.api.common.query.options.SelectTest;

public class RequestParamsTest {

	private String filterString;
	private String selectString;
	private String expandString;
	private String orderbyString;
	private String offsetString;
	private String limitString;
	
	private Filter filter;
	private Select select;
	private Expand expand;
	private Orderby orderby;
	private Offset offset;
	private Limit limit;

	@Before
	public void before() {
		filter = FilterTest.create1(1);
		select = SelectTest.create(1);
		expand = ExpandTest.create(1);
		orderby = OrderbyTest.create(1);
		offset = OffsetTest.create(1);
		limit = LimitTest.create(1);
		
		filterString = Options.getToOptionHelper(Filter.FILTER_PREFIX)
				.addOption(FilterTest.create(1))
				.addConnector(Operator.And)
				.addOption(FilterTest.create(1))
				.get();
		selectString = select.getOptionAsString();
		expandString = expand.getOptionAsString();
		orderbyString = orderby.getOptionAsString();
		offsetString = offset.getOptionAsString();
		limitString = limit.getOptionAsString();
	}

	@Test
	public void test() {

		System.out.println(create().get());
		System.out.println(create1());
		System.out.println(new RequestParams());
	}

	public RequestParams create() {

		RequestParams requestParams = new RequestParams();
		requestParams.setFilter(filter);
		requestParams.setSelect(select);
		requestParams.setExpand(expand);
		requestParams.setOrderby(orderby);
		requestParams.setOffset(offset);

		return requestParams;
	}
	
	
	public RequestParams create1() {
		RequestParams requestParams = new RequestParams(filterString,selectString,expandString,orderbyString,offsetString,"");
		return requestParams;
	}
}
