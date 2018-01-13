package com.hc.sap.anywhere.api.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hc.sap.anywhere.api.common.HttpClient;

public class BaseApi{

    private static Logger logger = LoggerFactory.getLogger(BaseApi.class);

    
    protected HttpClient httpClient;

    /**
     * 支持多App时,可扩张
     * 
     * @param httpClient
     */
    public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}
	
}
