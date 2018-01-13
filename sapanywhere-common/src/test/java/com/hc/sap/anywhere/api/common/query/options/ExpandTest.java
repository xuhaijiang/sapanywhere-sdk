package com.hc.sap.anywhere.api.common.query.options;


import org.junit.Test;

import com.hc.sap.anywhere.api.common.query.options.Expand;

public class ExpandTest {

	@Test
    public void test(){
		System.out.println(new Expand("id","name").getOptionAsString());
	}
	
	public static Expand create(int num){
		return new Expand("id" + num, "name"+ num);
	}
	
}
