package com.hc.sap.anywhere.api.common.base.language;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hc.sap.anywhere.api.common.util.LanguageCodeDeserializer;
import com.hc.sap.anywhere.api.common.util.LanguageCodeSerializer;

/**
 * Language the customer speaking. Language code is the ISO code.
 * 
 * @author xuhaijiang
 *
 */
public class LanguageInfo {

	@JsonProperty(value = "id")
	private long id;
	@JsonProperty(value = "code")
	@JsonSerialize(using = LanguageCodeSerializer.class)
	@JsonDeserialize(using = LanguageCodeDeserializer.class)
	private LanguageCode code;

	public LanguageInfo() {
	}

	public LanguageInfo(LanguageCode code) {
		this.code = code;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LanguageCode getCode() {
		return code;
	}

	public void setCode(LanguageCode code) {
		this.code = code;
	}

}
