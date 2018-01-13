package com.hc.sap.anywhere.api.user;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.hc.sap.anywhere.api.common.base.language.LanguageCode;
import com.hc.sap.anywhere.api.common.query.Options;
import com.hc.sap.anywhere.api.common.query.Querys;
import com.hc.sap.anywhere.api.common.query.options.Filter;
import com.hc.sap.anywhere.api.common.query.options.Limit;
import com.hc.sap.anywhere.api.common.query.options.Offset;
import com.hc.sap.anywhere.api.common.query.options.Operator;
import com.hc.sap.anywhere.api.common.query.options.Orderby;
import com.hc.sap.anywhere.api.common.query.options.Select;
import com.hc.sap.anywhere.api.common.util.JsonMapper;
import com.hc.sap.anywhere.api.user.Users;
import com.hc.sap.anywhere.api.user.bean.User;

public class UsersTest {

	@Test
	public void getUsersDefaultTest() throws Exception {

		Filter filter = new Filter<String, String>("email", Operator.Equals, "xuhaijiang@zjhcsoft.com");
		Orderby orderby = new Orderby(Operator.ORDERBY_ASC, "email");
		Select select = new Select("id","email");
		Offset offset = new Offset(1);
		Limit limit = new Limit(5);
		
		//String queryOptions = Options.getSimpleOption(offsetAndLimit);
		
		String queryOptions = Querys.getQueryOptions("")
		.appendOption(Options.getSimpleOption(filter))
		.appendOption(orderby.getOptionAsString())
		.appendOption(Options.getSimpleOption(select))
		.appendOption(offset.getOptionAsString())
		.appendOption(limit.getOptionAsString())
		.get();
		
		String userJsons = (Users.defaults().getUsersDefault(queryOptions));
		System.out.println(userJsons);
		
		List<User> user = null;//Users.defaults().jsonToUserList(userJsons);
		Assert.assertNotNull(user);
		for (User user2 : user) {
			System.out.println(user2.getId() + " " + user2.getCode() + " " + user2.getEmail());
		}
	}

	@Test
	public void getCurrentUserDefaultTest() {
		String userJson = Users.defaults().getCurrentUserDefault();
		System.out.println(userJson);
		
		User user = Users.defaults().jsonToUser(userJson);
		Assert.assertNotNull(user);
		System.out.println(user.getCode() +"\t"+ user.getEmail());
	}

	@Test(expected=NullPointerException.class)
	public void testJsonBean() throws Exception {
		User User = createBean();
		
		String json = JsonMapper.nonDefaultMapper().toJson(User);
		
		System.out.println(json);
		
		User bean = JsonMapper.nonDefaultMapper().fromJson(json, User.class);
	
		System.out.println(bean.getEmail());
		
		String json1 = JsonMapper.nonDefaultMapper().toJson(bean);
		System.out.println(json1);
	}
	
	public static User createBean(){
		User user = new User();
		user.setId(1111l);
		user.setCode("xhj Code");;
		user.setCompanyCode("Company Code");
		user.setEmail("Email");
		user.setLanguage(LanguageCode.en_US);
		
		return user;
	}

}
