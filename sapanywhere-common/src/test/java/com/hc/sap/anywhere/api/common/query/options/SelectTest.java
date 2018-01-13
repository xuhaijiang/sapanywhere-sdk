package com.hc.sap.anywhere.api.common.query.options;


import org.junit.Test;

import com.hc.sap.anywhere.api.common.query.options.Select;

public class SelectTest {

	@Test
    public void test(){
		System.out.println(new Select("id","name").getOptionAsString());
	}
	
	public static Select create(int num){
		return new Select("id" + num, "name"+ num);
	}
	
}
