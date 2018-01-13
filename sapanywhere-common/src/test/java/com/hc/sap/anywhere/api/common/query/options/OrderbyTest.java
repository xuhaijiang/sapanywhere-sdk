package com.hc.sap.anywhere.api.common.query.options;

import org.junit.Test;

import com.hc.sap.anywhere.api.common.query.options.Operator;
import com.hc.sap.anywhere.api.common.query.options.Orderby;

public class OrderbyTest {

	@Test
    public void test(){
		System.out.println(create(1).getOptionAsString());
		System.out.println(create1(create(2),create(3)).getOptionAsString());
	}
	
	public static Orderby create(int num){
		return new Orderby(Operator.ORDERBY_DESC ,"id"+num,"name"+num);
	}
	
	public static Orderby create1(Orderby... orderby){
		return new Orderby(orderby);
	}
}
