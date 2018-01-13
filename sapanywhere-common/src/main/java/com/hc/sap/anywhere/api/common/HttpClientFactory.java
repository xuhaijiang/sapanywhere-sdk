package com.hc.sap.anywhere.api.common;

import java.util.concurrent.ConcurrentHashMap;

import com.hc.sap.anywhere.api.common.HttpClient;
import com.hc.sap.anywhere.api.common.base.AppSetting;
import com.hc.sap.anywhere.api.common.base.Endpoint;
import com.hc.sap.anywhere.api.common.oauth2.AccessTokenHolder;
import com.hc.sap.anywhere.api.common.oauth2.DefaultAccessTokenHolder;

public class HttpClientFactory {

    private static HttpClientFactory instance = null;
    private static ConcurrentHashMap<String, HttpClient> httpClients = new ConcurrentHashMap<>();

    private HttpClientFactory() {
    }

    public synchronized static HttpClientFactory getInstance() {
        if (instance == null) {
            instance = new HttpClientFactory();
        }
        return instance;
    }

    public HttpClient defaultWxClient() {
        return with(AppSetting.defaultSettings());
    }

    public HttpClient with(AppSetting appSetting) {
        if (!httpClients.containsKey(key(appSetting))) {
            String url = Endpoint.get("url.token.get");
            String clazz = appSetting.getTokenHolderClass();

            AccessTokenHolder accessTokenHolder = null;
            if(clazz == null || "".equals(clazz)) {
                try {
                    accessTokenHolder = (AccessTokenHolder)Class.forName(clazz).newInstance();
                    accessTokenHolder.setClientId(appSetting.getClientId());
                    accessTokenHolder.setClientSecret(appSetting.getClientSecret());
                    accessTokenHolder.setTokenUrl(url);
                } catch (Exception e) {
                    accessTokenHolder = new DefaultAccessTokenHolder(url, appSetting.getClientId(), appSetting.getClientSecret(),appSetting.getGrantType(),appSetting.getRefreshToken());
                }
            } else {
                accessTokenHolder = new DefaultAccessTokenHolder(url, appSetting.getClientId(), appSetting.getClientSecret(),appSetting.getGrantType(),appSetting.getRefreshToken());
            }

            HttpClient httpClient = new HttpClient(appSetting.getClientId(), appSetting.getClientSecret(), accessTokenHolder);
            httpClients.putIfAbsent(key(appSetting), httpClient);
        }

        return httpClients.get(key(appSetting));
    }

    private String key(AppSetting appSetting) {
        return appSetting.getClientId() + ":" + appSetting.getClientSecret();
    }
}

