package com.hc.sap.anywhere.api.common.query.options;

import static com.google.common.base.Preconditions.checkState;

import com.hc.sap.anywhere.api.common.query.AbstractOption;

public class Limit extends AbstractOption{

	private static final int MAX_LIMIT = 100;
	public final static String LIMIT_PREFIX = "limit=";
	
	public Limit(int limit){
		checkState(limit <= MAX_LIMIT);
		this.optionValue = String.valueOf(limit);
	}

	@Override
	public String getOptionAsString() {
		return LIMIT_PREFIX + (NEED_OPTION_ENCODER ? encoder(optionValue) : optionValue);
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
