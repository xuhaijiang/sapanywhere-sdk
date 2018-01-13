package com.hc.sap.anywhere.api.common.query.options;

import java.util.Objects;

import com.google.common.base.Joiner;
import com.hc.sap.anywhere.api.common.query.AbstractOption;

public class Orderby extends AbstractOption{
	
	public final static String ORDERBY_PREFIX = "orderby=";
	private String[] properties;
	private Operator operator;
	
	/**
	 * ID,NAME ASC
	 * @param operator
	 * @param properties
	 */
	public Orderby(Operator operator,String... properties){
		Objects.requireNonNull(properties);
		Objects.requireNonNull(operator);
		this.properties = properties;
		this.operator = operator;
		this.optionValue = createOptionValue();
	}
	
	/**
	 * ID DESC, NAME,DEPT ASC
	 * @param orderby
	 */
	public Orderby(Orderby... orderby){
		Objects.requireNonNull(orderby);
		StringBuffer sb = new StringBuffer();
		for (Orderby orderby1 : orderby) {
			sb.append(orderby1.getOptionValue());
			sb.append(",");
		}
		this.optionValue =  sb.substring(0, sb.length() -1).toString();
	}
	
	private String createOptionValue() {
		return Joiner.on(",").join(this.properties) + " " + operator.getOperator();
	}
	
	@Override
	public String getOptionAsString() {
		return ORDERBY_PREFIX + (NEED_OPTION_ENCODER ? encoder(optionValue) : optionValue);
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.optionValue.equals(((Orderby) obj).optionValue);
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

