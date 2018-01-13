package com.hc.sap.anywhere.api.common.query.options;

import static com.google.common.base.Preconditions.checkState;

import com.hc.sap.anywhere.api.common.query.AbstractOption;

public class Offset extends AbstractOption{

	public final static String OFFSET_PREFIX = "offset=";
	
	public Offset(int offset){
		checkState(offset >= 0);
		this.optionValue = String.valueOf(offset);
	}

	@Override
	public String getOptionAsString() {
		return OFFSET_PREFIX + (NEED_OPTION_ENCODER ? encoder(optionValue) : optionValue);
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
