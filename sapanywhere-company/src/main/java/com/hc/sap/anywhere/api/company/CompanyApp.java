package com.hc.sap.anywhere.api.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hc.sap.anywhere.api.common.AnwServiceCall;
import com.hc.sap.anywhere.api.common.BaseApi;
import com.hc.sap.anywhere.api.common.HttpClientFactory;
import com.hc.sap.anywhere.api.common.base.AppSetting;
import com.hc.sap.anywhere.api.common.base.Endpoint;
import com.hc.sap.anywhere.api.common.util.JsonMapper;
import com.hc.sap.anywhere.api.company.bean.Company;

public class CompanyApp extends BaseApi{

    private static Logger logger = LoggerFactory.getLogger(CompanyApp.class);

	public static CompanyApp defaults() {
		return with(AppSetting.defaultSettings());
	}

	public static CompanyApp with(AppSetting appSetting) {
		CompanyApp companyApp = new CompanyApp();
		companyApp.setHttpClient(HttpClientFactory.getInstance().with(appSetting));
		return companyApp;
	}

    /**
     * Get company info.
     * @return String
     */
    public String getCompanyInfo() {
        String url = Endpoint.get("url.company.get");
    	logger.debug("Get company info");
    	String companyJson = AnwServiceCall.get(httpClient, url).getContent();
        logger.debug("Get company info response body: {}", companyJson);
        return companyJson;
    }
    
    //此类暂不使用
    public Company  getCurrentCompany() {
        String companyJson = getCompanyInfo();
        return jsonToCompany(companyJson);
    }
    
    public Company jsonToCompany(String companyJson) {
        return JsonMapper.nonEmptyMapper().fromJson(companyJson, Company.class);
    }

}
