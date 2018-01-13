package com.hc.sap.anywhere.api.common;

import java.io.IOException;
import java.net.URI;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hc.sap.anywhere.api.common.oauth2.AccessToken;
import com.hc.sap.anywhere.api.common.oauth2.AccessTokenHolder;
import com.hc.sap.anywhere.api.common.oauth2.DefaultAccessTokenHolder;
import com.hc.sap.anywhere.api.common.response.SimpleResponse;
import com.hc.sap.anywhere.api.common.util.JsonMapper;

public class HttpClient {

    private static Logger logger = LoggerFactory.getLogger(HttpClient.class);

    protected CloseableHttpClient httpClient;
    private String clientId;
    private String clientSecret;
    private AccessTokenHolder accessTokenHolder;

    public HttpClient() {
        httpClient = create();
    }

    public CloseableHttpClient create(){
    	
    	 SSLContext sslContext = null;
         try {
             sslContext = SSLContexts.custom().loadTrustMaterial(new TrustStrategy() {
                 public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                     return true;
                 }
             }).build();
         } catch (GeneralSecurityException e) {
         	logger.error("Error while customizing SSL for HTTPS: " + e.getMessage());
         } 

         return HttpClients.custom().setSSLContext(sslContext).build();
    }
    
    public HttpClient(String tokenUrl, String clientId, String clientSecret,String grantType,String refreshToken) {
        this(clientId, clientSecret, new DefaultAccessTokenHolder(tokenUrl, clientId, clientSecret,grantType,refreshToken));
    }

    public HttpClient(String clientId, String clientSecret, AccessTokenHolder accessTokenHolder) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.accessTokenHolder = accessTokenHolder;
        httpClient = create();
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
    
    public void refreshToken() {
        accessTokenHolder.refreshToken();
    }

    public AccessToken getAccessToken() {
        return accessTokenHolder.getAccessToken();
    }
    
    /**
     * HTTP GET request, retrieving data by object URL.
     * 
     * @param uri - URL used for connection
     * @return SimpleResponse - object represents HTTP response with HTTP code and content
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public SimpleResponse get(final URI uri)
            throws KeyManagementException, NoSuchAlgorithmException, IOException {

        return get(uri, new SimpleResponse());
    }

    /**
     * HTTP GET request, retrieving data by object URL.
     * 
     * @param uri - URL used for connection
     * @param response - response object, which stores information about content and HTTP code
     * @return SimpleResponse - object represents HTTP response with HTTP code and content
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public <T extends SimpleResponse> T get(final URI uri, final T response)
            throws KeyManagementException, NoSuchAlgorithmException, IOException {

        return executeRequest(new HttpGet(uri), response);
    }

    /**
     * Create data witch POST request, by object URL and object payload.
     * 
     * @param url - URL used for connection
     * @param payload - data which will be send
     * @return SimpleResponse - object represents HTTP response with HTTP code and content
     * @throws IOException
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
    public SimpleResponse post(final URI url, final Object payload)
            throws IOException, KeyManagementException, NoSuchAlgorithmException {

        return post(url, payload, new SimpleResponse());
    }

    /**
     * Create data by POST request, by object URL and object payload.
     * 
     * @param url - URL used for connection
     * @param payload - data which will be send
     * @param response - object used for storing information about HTTP response
     * @return SimpleResponse - object represents HTTP response with HTTP code and content
     * @throws IOException
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
    public <T extends SimpleResponse> T post(final URI url, final Object payload, final T response)
            throws IOException, KeyManagementException, NoSuchAlgorithmException {

        return post(url, JsonMapper.nonDefaultMapper().toJson(payload), response);
    }

    /**
     * Create data by POST request, by object URL and object payload.
     * 
     * @param url - URL used for connection
     * @param json - data which will be send
     * @return SimpleResponse - object represents HTTP response with HTTP code and content
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public SimpleResponse post(URI url, String json)
            throws KeyManagementException, NoSuchAlgorithmException, IOException {

        return post(url, json, new SimpleResponse());
    }

    /**
     * Create data by POST request, by object URL and object payload.
     * 
     * @param url - URL used for connection
     * @param json - data which will be send
     * @param response - object used for storing information about HTTP response
     * @return SimpleResponse - object represents HTTP response with HTTP code and content
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public <T extends SimpleResponse> T post(final URI url, final String json, final T response)
            throws KeyManagementException, NoSuchAlgorithmException, IOException {

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept", ContentType.APPLICATION_JSON.getMimeType());
        httpPost.setHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
        httpPost.setEntity(new StringEntity(json == null ? "" : json, ContentType.APPLICATION_JSON));

        return executeRequest(httpPost, response);
    }

    /**
     * Update data witch PATCH request, by object URL and object payload.
     * 
     * @param url - URL used for connection
     * @param payload - data which will be send
     * @return SimpleResponse - object represents HTTP response with HTTP code and content
     * @throws IOException
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
    public SimpleResponse patch(final URI url, final Object payload)
            throws IOException, KeyManagementException, NoSuchAlgorithmException {

        return patch(url, payload, new SimpleResponse());
    }

    /**
     * Update data witch PATCH request, by object URL and object payload.
     * 
     * @param url - URL used for connection
     * @param payload - data which will be send
     * @param response - object used for storing information about HTTP response
     * @return SimpleResponse - object represents HTTP response with HTTP code and content
     * @throws IOException
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
    public <T extends SimpleResponse> T patch(final URI url, final Object payload, final T response)
            throws IOException, KeyManagementException, NoSuchAlgorithmException {

        return patch(url, JsonMapper.nonDefaultMapper().toJson(payload), response);
    }

    /**
     * Update data witch PATCH request, by object URL and JSON string.
     * 
     * @param url - URL used for connection
     * @param json - data which will be send
     * @return SimpleResponse - object represents HTTP response with HTTP code and content
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
    public SimpleResponse patch(final URI url, final String json)
            throws KeyManagementException, NoSuchAlgorithmException {

        return patch(url, json, new SimpleResponse());
    }

    /**
     * Update data witch PATCH request, by object URL and JSON string and returns data in response object.
     * 
     * @param url - URL used for connection
     * @param json - data which will be send
     * @param SimpleResponse - object represents HTTP response with HTTP code and content
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
    public <T extends SimpleResponse> T patch(URI url, String json, T response)
            throws KeyManagementException, NoSuchAlgorithmException {

        HttpPatch httpPatch = new HttpPatch(url);
        httpPatch.setHeader("Accept", ContentType.APPLICATION_JSON.getMimeType());
        httpPatch.setEntity(new StringEntity(json == null ? "" : json, ContentType.APPLICATION_JSON));

        return executeRequest(httpPatch, response);
    }

    /**
     * Method, which executes request and returns response in SimpleResponse object.
     * 
     * @param httpUriRequest
     * @return SimpleResponse - object represents HTTP response with HTTP code and content
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public SimpleResponse executeRequest(final HttpUriRequest httpUriRequest)
            throws NoSuchAlgorithmException, KeyManagementException {

        return executeRequest(httpUriRequest, new SimpleResponse());
    }
    
    
    public <T extends SimpleResponse> T executeRequest(final HttpUriRequest httpUriRequest, final T response)
            throws NoSuchAlgorithmException, KeyManagementException {

        try {
            final HttpResponse httpResponse = httpClient.execute(httpUriRequest);
            response.setResponse(httpResponse);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	//不关闭，复用
            //close(httpClient);
        }

        return response;
    }
    
    private void close(final CloseableHttpClient httpClient) {
        if (httpClient != null) {
            try {
                httpClient.close();
            } catch (IOException e) {
            	logger.warn("IOException: " + e.getMessage());
            }
        }
    }
}
