package com.hc.sap.anywhere.api.common.base.language;

/**
 * Language culture code, format in {language}-{region}. Language code is
 * defined as ISO 639-1. Region code is defined as ISO 3166-1 alpha-2.
 * 
 * @author xuhaijiang
 *
 */
public enum LanguageCode {

	zh_CN("zh-CN"), en_US("en-US");

	private String code;

	private LanguageCode(String code) {
		this.code = code;
	}

	public static LanguageCode fromCode(String code) {
		for (LanguageCode gender : LanguageCode.values()) {
			if (gender.code == code) {
				return gender;
			}
		}
		return LanguageCode.en_US;
	}

	public String getCode() {
		return this.code;
	}
}
