package com.hc.sap.anywhere.api.common.query;

import org.junit.Before;
import org.junit.Test;

import com.hc.sap.anywhere.api.common.query.Querys;

public class QuerysTest {

	private String url = "http://www.baidu.com?a=a";
	
	private String filters;
	private String orderbys;
	private String limits;
	private String offsets;
	private String selects;
	private String expand;
	
	@Before
	public void before(){
		filters = OptionsTest.testFilter();
		orderbys = OptionsTest.testOrderby();
		offsets = OptionsTest.testOffset();
		limits = OptionsTest.testLimit();
		selects = OptionsTest.testSelect();
		expand = OptionsTest.testExpand();
	}
	
	@Test
	public  void getQueryString() {
		System.out.println(Querys.getQueryOptions(url,filters,orderbys,offsets,limits,selects,expand));
	}
	
	@Test
	public  void getQueryHelper() {
		
		System.out.println(Querys.getQueryOptions(url)
				.appendOption(filters)
				.appendOption(orderbys)
				.appendOption(offsets)
				.appendOption(limits)
				.appendOption(selects)
				.appendOption(expand)
				.get());
	}
}
