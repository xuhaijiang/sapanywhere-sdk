package com.hc.sap.anywhere.api.common;

import java.net.HttpURLConnection;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.hc.sap.anywhere.api.common.response.AnwSimpleResponse;

/**
 * Class, which contains helper methods used for making HTTP calls to SAP Anywhere.
 */
public class AnwServiceCall {

    private static final Logger logger = LoggerFactory.getLogger(AnwServiceCall.class);

    /**
     * Method, which make HTTP GET requests.
     * 
     * @param url - string URL for connection
     * @return instance of AnwSimpleResponse containing response
     */
    public static AnwSimpleResponse get(HttpClient httpClient,String url) {
        AnwSimpleResponse response = null;
        try {
            response = httpClient.get(new URI(appendAccessToken(httpClient, url)), new AnwSimpleResponse(appendAccessToken(httpClient, url)));
            if (responseRequiresAccessTokenRefresh(response)) {
                url = loadAndUpdateAccessToken(httpClient,url);
                response = httpClient.get(new URI(url), new AnwSimpleResponse(url));
            }
        } catch (Exception e) {
            logger.error("Error while executing: " + url);
            logger.error("Exception: " + e.getMessage());
        }
        return response;
    }

    /**
     * Method, which make HTTP POST requests.
     * 
     * @param url - string URL for connection
     * @param data - data which will be send via HTTP POST
     * @return instance of AnwSimpleResponse containing response
     */
    public static AnwSimpleResponse post(HttpClient httpClient,String url, final String data) {
        AnwSimpleResponse response = null;
        try {
            response = httpClient.post(new URI(appendAccessToken(httpClient, url)), data, new AnwSimpleResponse(appendAccessToken(httpClient, url)));
            if (responseRequiresAccessTokenRefresh(response)) {
                url = loadAndUpdateAccessToken(httpClient,url);
                response = httpClient.post(new URI(url), data, new AnwSimpleResponse(url));
            }
        } catch (Exception e) {
            logger.error("Error while executing: " + url);
            logger.error("Exception: " + e.getMessage());
        }
        return response;
    }


    /**
     * Method, which make HTTP PATCH requests.
     * 
     * @param url - string URL for connection
     * @param data - data which will be send via HTTP PATCH
     * @return instance of AnwSimpleResponse containing response
     */
    public static AnwSimpleResponse patch(HttpClient httpClient,String url, final String data) {
        AnwSimpleResponse response = null;
        try {
            response = httpClient.patch(new URI(appendAccessToken(httpClient, url)), data, new AnwSimpleResponse(appendAccessToken(httpClient, url)));
            if (responseRequiresAccessTokenRefresh(response)) {
                url = loadAndUpdateAccessToken(httpClient,url);
                response = httpClient.patch(new URI(url), data, new AnwSimpleResponse(url));
            }
        } catch (Exception e) {
            logger.error("Error while executing: " + url);
            logger.error("Exception: " + e.getMessage());
        }
        return response;
    }

    /**
     * Method, which identifies, whether it is necessary to refresh access token or not. Blank value of access token might cause
     * returning of HTTP STATUS 500 - Internal Server Error.
     * 
     * @param response - response with response data used for verification of access token validity
     * @return true - it is necessary to refresh access token <br>
     *         false - it not necessary to refresh access token <br>
     */
    private static boolean responseRequiresAccessTokenRefresh(final AnwSimpleResponse response) {
        return (response.getStatusCode() == HttpURLConnection.HTTP_INTERNAL_ERROR && isBlankAccessToken(response.getUrl()))
                || response.getStatusCode() == HttpURLConnection.HTTP_FORBIDDEN
                || response.getStatusCode() == HttpURLConnection.HTTP_UNAUTHORIZED;
    }

    /**
     * Method, which loads new access token and attach it to the entered URL.
     * 
     * @param oldUrl - old url with expired access token
     * @return new URL with new valid access token
     */
    private static String loadAndUpdateAccessToken(HttpClient httpClient, final String oldUrl) {
        String updatedUrl = oldUrl;
        try {
        	httpClient.refreshToken();
            Matcher m = Pattern.compile("(.*access_token=).*?(&.*|$)").matcher(oldUrl);
            if (m.matches() && m.groupCount() >= 2) {
                updatedUrl = m.group(1);
                updatedUrl += httpClient.getAccessToken().getAccessToken();
                if (m.groupCount() > 2) {
                    updatedUrl += m.group(2);
                }
            }else{
            	updatedUrl = appendAccessToken(httpClient, updatedUrl);
            }
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage());
        }
        return updatedUrl;
    }

    /**
     * Method, which identifies, whether access token is missing or not.
     * 
     * @param url - URL used for identification whether access token is missing or not
     * @return true - access token is missing <br>
     *         false - access token is not missing <br>
     */
    private static boolean isBlankAccessToken(final String url) {
        Matcher m = Pattern.compile(".*access_token=(.*?)(&.*|$)").matcher(url);
        if (m.matches() && m.groupCount() >= 2) {
            String accessToken = m.group(1);
            return Strings.isNullOrEmpty(accessToken);
        }
        return true;
    }
    
    
    private static String appendAccessToken(HttpClient httpClient,String url) {
        String token = httpClient.getAccessToken().getAccessToken();
        logger.debug("[{}]:access token: {}", httpClient.getClientId(), token);
        return url + (url.indexOf('?') == -1 ? "?access_token=" + token : "&access_token=" + token);
    }
    
}
