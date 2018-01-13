package com.hc.sap.anywhere.api.common.query;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.google.common.collect.Lists;
import com.hc.sap.anywhere.api.common.query.options.Filter;
import com.hc.sap.anywhere.api.common.query.options.Operator;
import com.hc.sap.anywhere.api.common.query.options.Orderby;

public class Options {

	public static ToOptionHelper getToOptionHelper(String optionName) {
		return new ToOptionHelper(optionName);
	}
	
	public static <K, V> String getSimpleOption(AbstractOption abstractOption) {
		Objects.requireNonNull(abstractOption);
		return abstractOption.getOptionAsString();
	}

	public static String getFilterOrOrderbyOptionFromList(List<AbstractOption> abstractOptions, Operator operator) {
		Objects.requireNonNull(abstractOptions);
		if(Integer.compare(1, abstractOptions.size()) == 0){
			return getSimpleOption(abstractOptions.get(0));
		}else{
			AbstractOption abstractOption = abstractOptions.get(0);
			abstractOptions.remove(abstractOption);
			for (AbstractOption options1 : abstractOptions) {
				if(!abstractOptions.contains(abstractOptions)){
					if(options1 instanceof Filter){
						abstractOption = new Filter(abstractOption,operator,options1);
					}
					if(options1 instanceof Orderby){
						abstractOption = new Orderby((Orderby)options1);
					}
				}
			}
			return getSimpleOption(abstractOption);
		}
	}
	
	public static <K, V> String getFilterOptionFromMap(Map<K, V> filters, Operator operator) {
		Objects.requireNonNull(filters);
		List<AbstractOption> ls =createFilterOptionFromMap(filters, operator);
		return getFilterOrOrderbyOptionFromList(ls,operator);
	}
	
	public static <K, V> List<AbstractOption> createFilterOptionFromMap(Map<K, V> filters, Operator operator) {
		Objects.requireNonNull(filters);
		List<AbstractOption> ls = Lists.newArrayList();
		for (Map.Entry<K, V> entry : filters.entrySet()) {
			if(entry.getKey() instanceof Filter){
				ls.add(new Filter<K, V>(entry.getKey(), operator, entry.getValue()));
			}
		}
		return ls;
	}
	
	
	public static final class ToOptionHelper {
		private final String optionName;
		private ValueHolder holderHead = new ValueHolder();
		private ValueHolder holderTail = holderHead;

		private ToOptionHelper(String optionName) {
			this.optionName = Objects.requireNonNull(optionName);
		}
		
		public ToOptionHelper addOption(AbstractOption value) {
			return addHolder(value);
		}

		public ToOptionHelper addConnector(Operator connector) {
			return addHolder(connector);
		}
		
		public ToOptionHelper addConnector(String connector) {
			return addHolder(connector);
		}
		

		public String get() {
			StringBuilder builder = new StringBuilder(32).append(optionName);
			for (ValueHolder valueHolder = holderHead.next; valueHolder != null; valueHolder = valueHolder.next) {
				builder.append(valueHolder.get());
			}
			return builder.toString();
		}

		private ValueHolder addHolder() {
			ValueHolder valueHolder = new ValueHolder();
			holderTail = holderTail.next = valueHolder;
			return valueHolder;
		}

		
		private ToOptionHelper addHolder(Object value) {
			ValueHolder valueHolder = addHolder();
			valueHolder.value = value;
			return this;
		}

		private static final class ValueHolder {
			Object value;
			ValueHolder next;
			
			public String get() {
				if(value instanceof Operator)
					value = " " + ((Operator)value).getOperator() + " ";
				return value.toString();
			}
			
		}
	}
}
