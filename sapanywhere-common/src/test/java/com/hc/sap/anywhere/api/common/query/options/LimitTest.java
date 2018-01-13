package com.hc.sap.anywhere.api.common.query.options;


import org.junit.Test;

import com.hc.sap.anywhere.api.common.query.options.Limit;

public class LimitTest {

	@Test
    public void test(){
		System.out.println(new Limit(5).getOptionAsString());
	}
	
	public static Limit create(int num){
		return new Limit(5 + num);
	}
	
}
