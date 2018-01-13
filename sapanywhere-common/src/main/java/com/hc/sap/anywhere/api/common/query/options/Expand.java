package com.hc.sap.anywhere.api.common.query.options;

import com.google.common.base.Joiner;
import com.hc.sap.anywhere.api.common.query.AbstractOption;

public class Expand extends AbstractOption{
	
	public final static String EXPAND_PREFIX = "expand=";
	
	public Expand(String... properties){
		this.optionValue = Joiner.on(",").join(properties);
	}
	
	@Override
	public String getOptionAsString() {
		return EXPAND_PREFIX + (NEED_OPTION_ENCODER ? encoder(optionValue) : optionValue);
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

