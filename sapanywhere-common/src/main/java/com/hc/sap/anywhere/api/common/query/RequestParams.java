package com.hc.sap.anywhere.api.common.query;

import java.util.Objects;

import com.google.common.base.Strings;
import com.hc.sap.anywhere.api.common.query.options.Expand;
import com.hc.sap.anywhere.api.common.query.options.Filter;
import com.hc.sap.anywhere.api.common.query.options.Limit;
import com.hc.sap.anywhere.api.common.query.options.Offset;
import com.hc.sap.anywhere.api.common.query.options.Orderby;
import com.hc.sap.anywhere.api.common.query.options.Select;

public class RequestParams {

	private StringBuffer builder = new StringBuffer("?");
	public RequestParams(){}
	
	public RequestParams(String... options){
		Objects.requireNonNull(options);
		for (String string : options) {
			if(!Strings.isNullOrEmpty(string)){
				builder.append(string);
				builder.append(Querys.AND);
			}
		}
	}
	
	public RequestParams setFilter(Filter filter) {
		this.setOption(filter);
		return this;
	}
	public RequestParams setSelect(Select select) {
		this.setOption(select);
		return this;
	}
	public RequestParams setExpand(Expand expand) {
		this.setOption(expand);
		return this;
	}
	public RequestParams setOrderby(Orderby orderby) {
		this.setOption(orderby);
		return this;
	}
	public RequestParams setOffset(Offset offset) {
		this.setOption(offset);
		return this;
	}
	public RequestParams setLimit(Limit limit) {
		this.setOption(limit);
		return this;
	}
	
	private RequestParams setOption(AbstractOption option) {
		builder.append(option.getOptionAsString()).append(Querys.AND);
		return this;
	}
	
	public String get() {
		return builder.toString();
	}
	
	@Override
	public String toString() {
		return get();
	}
}
