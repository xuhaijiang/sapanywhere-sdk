package com.hc.sap.anywhere.api.common.query;

import java.util.Objects;

import com.google.common.base.Strings;

public class Querys {

	public final static String AND = "&";

	public static String getQueryOptions(String url,String... options) {
		Objects.requireNonNull(options);
		StringBuffer builder = new StringBuffer(url + (url.indexOf('?') == -1 ? "?" : "&"));
		for (String string : options) {
			if(!Strings.isNullOrEmpty(string)){
				builder.append(string);
				builder.append(AND);
			}
		}
		return builder.substring(0, builder.length() - AND.length()).toString();
	}
	
	
	public static AppendQueryOptionHelper getQueryOptions(String url) {
		return new AppendQueryOptionHelper(url);
	}

	
	public static final class AppendQueryOptionHelper {
		private final String url;
		
		private OptionHolder holderHead = new OptionHolder();
		private OptionHolder holderTail = holderHead;

		public AppendQueryOptionHelper(String url) {
			this.url = Objects.requireNonNull(url);
		}
		
		public AppendQueryOptionHelper appendOption(String value) {
			return appendQueryOption(value);
		}
		
		
		private AppendQueryOptionHelper appendQueryOption(String option) {
			OptionHolder OptionHolder = appendQueryOption();
			OptionHolder.option = Objects.requireNonNull(option);
			return this;
		}

		private OptionHolder appendQueryOption() {
			OptionHolder optionHolder = new OptionHolder();
			holderTail = holderTail.next = optionHolder;
			return optionHolder;
		}

		private AppendQueryOptionHelper addHolder(String option) {
			OptionHolder optionHolder = appendQueryOption();
			optionHolder.option = Objects.requireNonNull(option);
			return this;
		}

		public String get() {
			StringBuffer builder = new StringBuffer(url + (url.indexOf('?') == -1 ? "?" : "&"));
			for (OptionHolder optionHolder = holderHead.next; optionHolder != null; optionHolder = optionHolder.next) {
				if (optionHolder.option != null && !optionHolder.option.toString().equals("")) {
					builder.append(optionHolder.option).append(AND);
				}
			}
			return builder.substring(0, builder.length() - AND.length()).toString();
		}

		private static final class OptionHolder {
			String option;
			OptionHolder next;
		}
	}

}
