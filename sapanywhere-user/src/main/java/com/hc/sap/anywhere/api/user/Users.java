package com.hc.sap.anywhere.api.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hc.sap.anywhere.api.common.AnwServiceCall;
import com.hc.sap.anywhere.api.common.BaseApi;
import com.hc.sap.anywhere.api.common.HttpClientFactory;
import com.hc.sap.anywhere.api.common.base.AppSetting;
import com.hc.sap.anywhere.api.common.base.Endpoint;
import com.hc.sap.anywhere.api.common.query.RequestParams;
import com.hc.sap.anywhere.api.common.util.JsonMapper;
import com.hc.sap.anywhere.api.user.bean.User;

public class Users extends BaseApi{

    private static Logger logger = LoggerFactory.getLogger(Users.class);

	public static Users defaults() {
		return with(AppSetting.defaultSettings());
	}

	public static Users with(AppSetting appSetting) {
		Users users = new Users();
		users.setHttpClient(HttpClientFactory.getInstance().with(appSetting));
		return users;
	}
    
    /**
     * Get a list of users.
     * @param queryOptions
     * @return
     * @throws Exception 
     */
    public String getUsersDefault(String queryOptions) {
    	String url = String.format(Endpoint.get("url.users.get"), queryOptions);
    	logger.debug("Get a list of users request params: {}", queryOptions);
    	String userJsons = AnwServiceCall.get(httpClient, url).getContent();
        logger.debug("Get a list of users response body: {}", userJsons);
        return userJsons;
    }
    
    /**
     * Get current user info.
     * @return String
     */
    public String getCurrentUserDefault() {
        String url = Endpoint.get("url.users.me.get");
    	logger.debug("Get current user info");
    	String userJson = AnwServiceCall.get(httpClient, url).getContent();
        logger.debug("Get current user info response body: {}", userJson);
        return userJson;
    }
    
    public String getUsersDefault(RequestParams requestParams) {
        return getUsersDefault(requestParams.toString());
    }
    
    public List<User> getUsers(RequestParams requestParams) {
    	String userJsons = getUsersDefault(requestParams);
    	return jsonToUserList(userJsons);
    }
    
    public List<User> getUsers(String queryOptions) {
    	String userJsons = getUsersDefault(queryOptions);
        return jsonToUserList(userJsons);
    }
    
    public List<User> jsonToUserList(String userJsons) {
    	return JsonMapper.nonEmptyMapper().fromJsons(userJsons, User.class);
    }
    
    public User getCurrentUser() {
        String userJson = getCurrentUserDefault();
        return jsonToUser(userJson);
    }
    
    public User jsonToUser(String userJson) {
        return JsonMapper.nonEmptyMapper().fromJson(userJson, User.class);
    }

}
