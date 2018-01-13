package com.hc.sap.anywhere.api.common.query;

import com.hc.sap.anywhere.api.common.util.URLEncoder;

public abstract class AbstractOption {

	protected String optionValue;
	protected static boolean NEED_OPTION_ENCODER = true;
	
	public abstract String getOptionAsString();
	public abstract String getOptionValue();
	
	public static String encoder(String option){
		return URLEncoder.encode(option);
	}
}
