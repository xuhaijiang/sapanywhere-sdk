package com.hc.sap.anywhere.api.common.query.options;

import com.google.common.base.Joiner;
import com.hc.sap.anywhere.api.common.query.AbstractOption;

public class Select extends AbstractOption{
	
	public final static String SELECT_PREFIX = "select=";
	
	public Select(String... properties){
		this.optionValue = Joiner.on(",").join(properties);
	}

	@Override
	public String getOptionAsString() {
		return SELECT_PREFIX + (NEED_OPTION_ENCODER ? encoder(optionValue) : optionValue);
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

