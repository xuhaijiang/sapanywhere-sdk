package com.hc.sap.anywhere.api.common.response;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hc.sap.anywhere.api.common.util.JsonMapper;

/**
 * Class, which represents response of SAP Anywhere HTTP calls. It contains information about error code, url, message or parsed
 * id from HTTP POST request.
 */
public class AnwSimpleResponse extends SimpleResponse {

    private static final Logger logger = LoggerFactory.getLogger(AnwSimpleResponse.class);

    @JsonProperty("errorCode")
    private String errorCode;
    @JsonProperty("message")
    private String message;
    private Long id;
    private String url;

    public AnwSimpleResponse() {
    }

    public AnwSimpleResponse(final String url) {
        super();
        setUrl(url);
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isError() {
        return getErrorCode() != null || (getStatusCode() != HttpStatus.SC_CREATED && getStatusCode() != HttpStatus.SC_OK);
    }

    public boolean hasId() {
        return getId() != null;
    }

    /**
     * Method, which parse HTTP response. It determines error code, message or id in HTTP POST request.
     */
    @Override
    public void setResponse(final HttpResponse httpResponse) {
        super.setResponse(httpResponse);
        if (getContent() != null) {
            if (getContent().contains("\"errorCode\"")) {
                AnwSimpleResponse errorResponse = JsonMapper.defaultMapper().fromJson(getContent(), AnwSimpleResponse.class);
				this.setErrorCode(errorResponse.getErrorCode());
				this.setMessage(errorResponse.getMessage());
            } else {
            	if (StringUtils.isNumericSpace(getContent()) && !getContent().isEmpty()) {
                    try {
                        setId(Long.parseLong(getContent()));
                    } catch (NumberFormatException e) {
                        logger.error("NumberFormatException " + e.getMessage());
                        logger.error("Error parsing SAP Anywhere Response: " + getContent() + "\n failed with: ", e);
                    }
                }
            }
        }
    }
}
