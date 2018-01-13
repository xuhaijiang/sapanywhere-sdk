package com.hc.sap.anywhere.api.company.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hc.sap.anywhere.api.common.base.language.LanguageCode;
import com.hc.sap.anywhere.api.common.util.LanguageCodeDeserializer;
import com.hc.sap.anywhere.api.common.util.LanguageCodeSerializer;

/**
 * @author zy
 */
public class Company {

	@JsonProperty(value = "code", access = Access.READ_ONLY)
	private String code;
	@JsonProperty(value = "name", access = Access.READ_ONLY)
	private String name;
	@JsonProperty(value = "timeZone", access = Access.READ_ONLY)
	private String timeZone;
	@JsonProperty(value = "locale", access = Access.READ_ONLY)
	@JsonSerialize(using = LanguageCodeSerializer.class)
	@JsonDeserialize(using = LanguageCodeDeserializer.class)
	private LanguageCode locale;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public LanguageCode getLocale() {
		return locale;
	}
	public void setLocale(LanguageCode locale) {
		this.locale = locale;
	}
 
}
