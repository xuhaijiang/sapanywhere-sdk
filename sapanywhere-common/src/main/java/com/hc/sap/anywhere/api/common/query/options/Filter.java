package com.hc.sap.anywhere.api.common.query.options;

import java.util.Date;
import java.util.Objects;

import com.hc.sap.anywhere.api.common.query.AbstractOption;

public class Filter<K, V> extends AbstractOption {

	public final static String FILTER_PREFIX = "filter=";

	private K key;
	private Operator operator;
	private V value;

	public Filter(K key, Operator operator, V value) {
		Objects.requireNonNull(key);
		Objects.requireNonNull(operator);
		Objects.requireNonNull(value);
		this.key = key;
		this.operator = operator;
		this.value = value;
		this.optionValue = createOptionValue();
	}

	private String createOptionValue() {
		if (key instanceof String) {
			return keyInstanceofString();
		} else if (key instanceof Filter) {
			return ((Filter) key).getOptionValue() + " " + operator.getOperator() + " " + ((Filter) value).getOptionValue();
		} else {
			return null;
		}
	}

	private String keyInstanceofString() {
		if (value instanceof String) {
			return "(" + key + " " + operator.getOperator() + " " + "'" + value + "'" + ")";
		} else if (value instanceof Date) {
			return "(" + key + " " + operator.getOperator() + " " + "'" + value + "'" + ")";
		} else {
			return "(" + key + " " + operator.getOperator() + " " +  value +  ")";
		}
	}

	@Override
	public String getOptionAsString() {
		
		return FILTER_PREFIX + (NEED_OPTION_ENCODER ? encoder(optionValue) : optionValue);
	}

	

	@Override
	public boolean equals(Object obj) {
		return this.optionValue.equals(((Filter<K, V>) obj).optionValue);
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