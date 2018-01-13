package com.hc.sap.anywhere.api.common.oauth2;

public class DefaultAccessTokenHolder extends AccessTokenHolder {

    private AccessToken accessToken;

    public DefaultAccessTokenHolder(String tokenUrl, String clientId, String clientSecret,String grantType,String refreshToken){
        super(tokenUrl, clientId, clientSecret, grantType, refreshToken);
    }

    @Override
    public AccessToken getAccessToken() {
        if (accessToken == null || accessToken.expired()) {
            refreshToken();
        }
        return accessToken;
    }

    @Override
    public void refreshToken() {
        if (accessToken == null || accessToken.expired()) {
            String content = fetchAccessToken();
            AccessToken accessToken = AccessToken.fromJson(content);
            this.accessToken = accessToken;
        }
    }

    @Override
    public void expireToken() {
        accessToken.setExpiresIn(-30);//强制设置为无效
    }


}
