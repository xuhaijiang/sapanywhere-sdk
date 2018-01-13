package com.hc.sap.anywhere.api.common.query.options;


import org.junit.Test;

import com.hc.sap.anywhere.api.common.query.options.Offset;

public class OffsetTest {

	@Test
    public void test(){
		System.out.println(new Offset(0).getOptionAsString());
	}
	
	public static Offset create(int num){
		return new Offset(0 + num);
	}
	
}
