package com.hc.sap.anywhere.api.company;

import org.junit.Assert;
import org.junit.Test;
import com.hc.sap.anywhere.api.common.base.language.LanguageCode;
import com.hc.sap.anywhere.api.common.util.JsonMapper;
import com.hc.sap.anywhere.api.company.bean.Company;
 
public class CompanyAppTest {

	@Test
	public void getCompanyInfoTest() {
		String companyJson = CompanyApp.defaults().getCompanyInfo();
		System.out.println(companyJson);
		
		//Company中各属性下     access = Access.READ_WRITE(有可写权限即可)时可测试输出内容
		Company company  = CompanyApp.defaults().jsonToCompany(companyJson);
		Assert.assertNotNull(company);
		System.out.println(company.getCode() +"\t"+ company.getName());
	}

	@Test 
	public void testJsonBean() throws Exception {
		Company company = createCompanyBean();	
		String json = JsonMapper.nonDefaultMapper().toJson(company);
		System.out.println(json);
		
		//Company中各属性下值    access = Access.READ_WRITE(有可写权限)时可测试输出内容
		Company bean = JsonMapper.nonDefaultMapper().fromJson(json, Company.class);
		System.out.println(bean.getName());	
		String json1 = JsonMapper.nonDefaultMapper().toJson(bean);
		System.out.println(json1);
	}
	
	public static Company createCompanyBean(){
		Company company = new Company();
		company.setCode("Company Code");
		company.setName("小贝有限公司");
		company.setTimeZone(" (+08:00) Etc/GMT+08:00 ");
		company.setLocale(LanguageCode.zh_CN);
		return company;
	}

}
