package com.hc.sap.anywhere.api.common.oauth2;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hc.sap.anywhere.api.common.response.SimpleResponse;



public abstract class AccessTokenHolder {

    private static Logger logger = LoggerFactory.getLogger(AccessTokenHolder.class);

    private String clientId;
    private String clientSecret;
    private String tokenUrl;
    private String grantType;
    private String refreshToken;
    private CloseableHttpClient httpClient;
 
    
    public AccessTokenHolder(String tokenUrl, String clientId, String clientSecret,String grantType,String refreshToken) {
        this.tokenUrl = tokenUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.grantType = grantType;
        this.refreshToken = refreshToken;
        
        httpClient = HttpClients.createDefault();
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }
    
    
    protected String fetchAccessToken() {
        logger.debug("[{}]:fetching a new access token.", clientId);
        String url = String.format(this.tokenUrl, this.clientId, this.clientSecret,this.grantType,this.refreshToken);
        HttpGet httpGet = new HttpGet(url);
        SimpleResponse simpleResponse = new SimpleResponse();
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
        	simpleResponse.setResponse(response);
        	return simpleResponse.getContent();
        } catch (Exception e) {
        	logger.error("Error while executing: " + url);
            logger.error("Exception: " + e.getMessage());
            return simpleResponse.getContent();
        }
    }

    /**
     * 获取access token
     * @return
     */
    public abstract AccessToken getAccessToken();

    /**
     * 强制刷新
     */
    public abstract void refreshToken();

    /**
     * 强制设置token过期
     */
    public abstract void expireToken();
}
