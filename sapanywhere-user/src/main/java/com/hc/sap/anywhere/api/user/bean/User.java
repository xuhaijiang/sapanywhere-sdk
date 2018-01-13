package com.hc.sap.anywhere.api.user.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hc.sap.anywhere.api.common.base.language.LanguageCode;
import com.hc.sap.anywhere.api.common.util.LanguageCodeDeserializer;
import com.hc.sap.anywhere.api.common.util.LanguageCodeSerializer;

/**
 * User. readonly
 * 
 * @author xuhaijiang
 *
 */
public class User {

	@JsonProperty(value = "id", access = Access.WRITE_ONLY)
	private long id;
	@JsonProperty(value = "code", access = Access.WRITE_ONLY)
	private String code;
	@JsonProperty(value = "email", access = Access.WRITE_ONLY)
	private String email;
	@JsonProperty(value = "language", access = Access.WRITE_ONLY)
	@JsonSerialize(using = LanguageCodeSerializer.class)
	@JsonDeserialize(using = LanguageCodeDeserializer.class)
	private LanguageCode language;
	@JsonProperty(value = "companyCode", access = Access.WRITE_ONLY)
	private String companyCode;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LanguageCode getLanguage() {
		return language;
	}

	public void setLanguage(LanguageCode language) {
		this.language = language;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
}
