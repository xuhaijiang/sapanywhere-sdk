package com.hc.sap.anywhere.api.appointmentactivity;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hc.sap.anywhere.api.common.AnwServiceCall;
import com.hc.sap.anywhere.api.common.BaseApi;
import com.hc.sap.anywhere.api.common.HttpClientFactory;
import com.hc.sap.anywhere.api.common.base.AppSetting;
import com.hc.sap.anywhere.api.common.base.Endpoint;
import com.hc.sap.anywhere.api.common.response.AnwSimpleResponse;

public class AppointmentActivitys extends BaseApi{
	
	private static Logger logger = LoggerFactory.getLogger(AppointmentActivitys.class);

    public static AppointmentActivitys defaults() {
        return with(AppSetting.defaultSettings());
    }

    public static AppointmentActivitys with(AppSetting appSetting) {
        AppointmentActivitys AppointmentActivitys = new AppointmentActivitys();
        AppointmentActivitys.setHttpClient(HttpClientFactory.getInstance().with(appSetting));
        return AppointmentActivitys;
    }

    /**
     * Get a list of appointment activities with paging (optional)
     * 
     * @param queryOptions 
     * @return
     */
    public AnwSimpleResponse getAppointmentActivityDefault(String queryOptions) {
        String url = String.format(Endpoint.get("url.appointmentactivities.get"), queryOptions);
    	return AnwServiceCall.get(httpClient, url);
    }
    
    /**
     * Create an appointment activity using the given data.
     * 
     * @param jsonContent JSON data of the appointment activity to be created
     * @return The id of the newly created appointment activity
     */
    public AnwSimpleResponse postAppointmentActivityDefault(String jsonContent) {
        String url = Endpoint.get("url.appointmentactivities.post");
        return AnwServiceCall.post(httpClient,url, jsonContent);
    }
}
