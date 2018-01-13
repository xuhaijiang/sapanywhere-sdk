package com.hc.sap.anywhere.api.common.query.options;

import com.hc.sap.anywhere.api.common.query.AbstractOption;


public class AccessToken extends AbstractOption{
	
	public final static String ACCESSTOKEN_PREFIX = "access_token=";
	
	public AccessToken(String accessToken){
		this.optionValue = accessToken;
	}
	
	@Override
	public String getOptionAsString() {
		return ACCESSTOKEN_PREFIX + (NEED_OPTION_ENCODER ? encoder(optionValue) : optionValue);
	}
	
	@Override
	public String getOptionValue() {
		return (NEED_OPTION_ENCODER ? encoder(optionValue) : optionValue);
	}
	
	@Override
	public String toString() {
		return getOptionValue();
	}
}