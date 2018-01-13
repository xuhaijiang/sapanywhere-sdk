package com.hc.sap.anywhere.api.appointmentactivity;

import java.util.Date;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.hc.sap.anywhere.api.appointmentactivity.bean.AppointmentActivity;
import com.hc.sap.anywhere.api.appointmentactivity.bean.enumeration.ActivityType;
import com.hc.sap.anywhere.api.appointmentactivity.bean.relation.ActivityCustomerInfo;
import com.hc.sap.anywhere.api.appointmentactivity.bean.relation.ParticipantInfo;
import com.hc.sap.anywhere.api.appointmentactivity.bean.relation.ParticipantType;
import com.hc.sap.anywhere.api.appointmentactivity.bean.relation.RelatedBO;
import com.hc.sap.anywhere.api.appointmentactivity.bean.relation.RelatedBoType;
import com.hc.sap.anywhere.api.common.base.user.UserInfo;
import com.hc.sap.anywhere.api.common.query.Options;
import com.hc.sap.anywhere.api.common.query.Querys;
import com.hc.sap.anywhere.api.common.query.RequestParams;
import com.hc.sap.anywhere.api.common.query.options.Filter;
import com.hc.sap.anywhere.api.common.query.options.Limit;
import com.hc.sap.anywhere.api.common.query.options.Offset;
import com.hc.sap.anywhere.api.common.query.options.Operator;
import com.hc.sap.anywhere.api.common.query.options.Orderby;
import com.hc.sap.anywhere.api.common.query.options.Select;
import com.hc.sap.anywhere.api.common.response.AnwSimpleResponse;
import com.hc.sap.anywhere.api.common.util.JsonMapper;

public class AppointmentActivitysTest {
	
	private Filter filter = new Filter("createdBy.id", Operator.Equals, 36);
	private Select select = new Select("id","title","createdBy");
	private Orderby orderby = new Orderby(Operator.ORDERBY_ASC, "id");
	private Offset offset = new Offset(1);
	private Limit limit = new Limit(5);
	
	
	@Test
	public void getAppointmentActivityDefaultTest() {
		
		String queryOptions = getQueryOptions();
		AnwSimpleResponse anwSimpleResponse = AppointmentActivitys.defaults().getAppointmentActivityDefault(queryOptions );
		System.out.println(anwSimpleResponse.getContent());
		
		try {
			Thread.sleep(6000l);  //sap anywhere 限制：2次调用间隔需大于5秒
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("---------分割线--------------");
		
		
		RequestParams requestParams = getRequestParams();
		AnwSimpleResponse anwSimpleResponse1 = AppointmentActivitys.defaults().getAppointmentActivityDefault(requestParams.get());
		System.out.println(anwSimpleResponse1.getContent());
	}
	
	@Test
	public void postAppointmentActivityDefaultTest() {
		String jsonContent = JsonMapper.nonDefaultMapper().toJson((Lists.newArrayList(createBean())));
		AnwSimpleResponse anwSimpleResponse = AppointmentActivitys.defaults().postAppointmentActivityDefault(jsonContent);
		System.out.println(anwSimpleResponse.getContent());
	}
	
	@Test
	public void testJsonBean() {
		AppointmentActivity appointmentActivity = createBean();
		
		String json = JsonMapper.nonDefaultMapper().toJson(appointmentActivity);
		
		System.out.println(json);
		
		AppointmentActivity aa = JsonMapper.nonDefaultMapper().fromJson(json, AppointmentActivity.class);
	
		System.out.println(aa.getStartTime());
	}
	
	public String getQueryOptions(){
		return Querys.getQueryOptions("?")
				.appendOption(Options.getSimpleOption(filter))
				.appendOption(orderby.getOptionAsString())
				.appendOption(Options.getSimpleOption(select))
				.toString();
	}
	
	public RequestParams getRequestParams(){
		return new RequestParams()
				.setFilter(filter)
				.setSelect(select)
				.setOrderby(orderby);
	}
	
	public static AppointmentActivity createBean(){
		AppointmentActivity appointmentActivity = new AppointmentActivity();
		appointmentActivity.setCreatedBy(new UserInfo("xhj"));
		appointmentActivity.setOwner(new UserInfo("xhj"));
		appointmentActivity.setTitle("xhj_code_test");
		appointmentActivity.setContent("xhj_code_test content");
		appointmentActivity.setRelatedBos(Lists.newArrayList(new RelatedBO(RelatedBoType.Lead, 1l),new RelatedBO(RelatedBoType.Lead, 2l)));
		
		ParticipantInfo[] participants = new ParticipantInfo[2];
		participants[0] = new ParticipantInfo(ParticipantType.ContactPerson, 1l);
		participants[1] = new ParticipantInfo(ParticipantType.ContactPerson, 2l);
		appointmentActivity.setParticipants(Lists.newArrayList(participants));
		
		ActivityCustomerInfo[] activityCustomers = new ActivityCustomerInfo[2];
		activityCustomers[0] = new ActivityCustomerInfo(1l);
		activityCustomers[1] = new ActivityCustomerInfo(2l);
		appointmentActivity.setActivityCustomers(Lists.newArrayList(activityCustomers));
		
		//appointmentActivity.setStartTime(DateUtils.getNowUTCZonedDateString());
		//appointmentActivity.setStartTime(Date.from(ZonedDateTime.now().toInstant()));
		appointmentActivity.setStartTime(new Date());
		appointmentActivity.setEndTime(new Date());
		appointmentActivity.setCreationTime(new Date());
		appointmentActivity.setCanceled(Boolean.TRUE);
		appointmentActivity.setCancelReason("cancel Reason");
		appointmentActivity.setCompleted(true);
		appointmentActivity.setType(ActivityType.DAILY);
		
		return appointmentActivity;
	}
}
