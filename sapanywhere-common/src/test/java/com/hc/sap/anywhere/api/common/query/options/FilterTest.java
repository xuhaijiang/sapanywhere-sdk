package com.hc.sap.anywhere.api.common.query.options;

import org.junit.Assert;
import org.junit.Test;

import com.hc.sap.anywhere.api.common.query.options.Filter;
import com.hc.sap.anywhere.api.common.query.options.Operator;


public class FilterTest {

	@Test
    public void test(){
		System.out.println(create(1).getOptionAsString());
		System.out.println(create1(2).getOptionAsString());
	}
	
	public static Filter<String,String> create(int num){
		return new Filter<String,String>("name" + num,Operator.Equals,"xhj" + num);
	}
	
	public static Filter<Filter,Filter> create1(int num){
		return new Filter<Filter,Filter>(create(num),Operator.And,create(num));
	}
}
