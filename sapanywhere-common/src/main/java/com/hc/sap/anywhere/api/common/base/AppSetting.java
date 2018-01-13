package com.hc.sap.anywhere.api.common.base;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hc.sap.anywhere.api.common.exception.AnwRuntimeException;
import com.hc.sap.anywhere.api.common.util.XmlObjectMapper;

public class AppSetting {

    private static Logger logger = LoggerFactory.getLogger(AppSetting.class);

    private static AppSetting appSetting = null;

    private String clientId;
    private String clientSecret;
    private String grantType;
    private String refreshToken;
    private String tokenHolderClass;

    public AppSetting() {
    }

    public AppSetting(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public static void setDefault(AppSetting appSetting) {
        AppSetting.appSetting = appSetting;
    }

    public static AppSetting defaultSettings() {
        if (appSetting == null) {
            loadFromSystemProperties();
        }

        if (appSetting == null) {
            loadFromClasspath();
        }

        if (appSetting == null) {
            throw new AnwRuntimeException(999, "当前系统没有设置缺省的clientId和clientSecret,请使用setDefault方法或者在classpath下面创建sap-anywhere-settings.xml文件.");
        }
        return appSetting;
    }

    private static void loadFromSystemProperties() {
        if (System.getProperties().contains("sap-anywhere-settings")) {
            logger.info("loading sap anywhere settings configuration from system properties...");
            String xml = System.getProperties().getProperty("sap-anywhere-settings", "");
            logger.info("sap-anywhere-settings: {}", xml);
            if (xml == null || "".equals(xml)) {
                return;
            } else {
                try {
                    AppSetting setting = XmlObjectMapper.defaultMapper().fromXml(xml, AppSetting.class);
                    appSetting = setting;
                } catch (IOException e) {
                }
            }
        }
    }

    private static void loadFromClasspath() {
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("sap-anywhere-settings-test.xml");

            if (inputStream == null) {
                inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("sap-anywhere-settings.xml");
            }

            if (inputStream != null) {
                String xml = IOUtils.toString(inputStream);
                AppSetting setting = XmlObjectMapper.defaultMapper().fromXml(xml, AppSetting.class);
                appSetting = setting;
            }
        } catch (IOException e) {
            logger.error("read settings from sap-anywhere-settings-test.xml or sap-anywhere-settings.xml failed:", e);
        }
    }

    public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getTokenHolderClass() {
        return tokenHolderClass;
    }

    public void setTokenHolderClass(String tokenHolderClass) {
        this.tokenHolderClass = tokenHolderClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppSetting that = (AppSetting) o;

        if (!clientId.equals(that.clientId)) return false;
        return clientSecret.equals(that.clientSecret);

    }

    @Override
    public int hashCode() {
        int result = clientId.hashCode();
        result = 31 * result + clientSecret.hashCode();
        return result;
    }
}
