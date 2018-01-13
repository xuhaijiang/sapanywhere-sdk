package com.hc.sap.anywhere.api.common.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.hc.sap.anywhere.api.common.exception.AnwRuntimeException;

public class Endpoint {

    private static Properties endpoints;

    private static synchronized void loadProperties() {
        if (endpoints == null) {
            try {
                Properties properties = new Properties();
                InputStream inputStream = Endpoint.class.getClassLoader().getResourceAsStream("endpoint.properties");
                properties.load(inputStream);
                endpoints = properties;
            } catch (IOException e) {
                throw new AnwRuntimeException(999, "cannot find resource endpoint.properties from classpath.");
            }
        }
    }

    public static String get(String key) {
        loadProperties();
        return endpoints.getProperty(key);
    }

}
