package com.hc.sap.anywhere.api.appointmentactivity.bean;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hc.sap.anywhere.api.appointmentactivity.bean.enumeration.ActivityType;
import com.hc.sap.anywhere.api.appointmentactivity.bean.relation.ActivityCustomerInfo;
import com.hc.sap.anywhere.api.appointmentactivity.bean.relation.ParticipantInfo;
import com.hc.sap.anywhere.api.appointmentactivity.bean.relation.RelatedBO;
import com.hc.sap.anywhere.api.common.base.user.UserInfo;

/**
 * Appointment Activity Master Data.
 * 
 * @author xuhaijiang
 *
 */
public class AppointmentActivity {

	/**
	 * readonly
	 */
	@JsonProperty(value = "id")
	private long id;

	/**
	 * readonly
	 */
	@JsonProperty(value = "createdBy")
	private UserInfo createdBy;

	/**
	 * readonly
	 */
	@JsonProperty(value = "owner")
	private UserInfo owner;

	@JsonProperty(value = "title")
	private String title;

	@JsonProperty(value = "content")
	private String content;

	@JsonProperty(value = "relatedBos")
	private List<RelatedBO> relatedBos;

	@JsonProperty(value = "participants")
	private List<ParticipantInfo> participants;

	@JsonProperty(value = "activityCustomers")
	private List<ActivityCustomerInfo> activityCustomers;

	/**
	 * string (date-time)
	 */
	@JsonProperty(value = "startTime")
	private Date startTime;
	
	/**
	 * string (date-time)
	 */
	@JsonProperty(value = "endTime")
	private Date endTime;
	
	/**
	 * readonly
	 */
	@JsonProperty(value = "creationTime")
	private Date creationTime;

	@JsonProperty(value = "canceled")
	private boolean canceled;
	@JsonProperty(value = "cancelReason")
	private String cancelReason;

	@JsonProperty(value = "completed")
	private boolean completed;

	@JsonProperty(value = "type")
	private ActivityType type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserInfo getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserInfo createdBy) {
		this.createdBy = createdBy;
	}

	public UserInfo getOwner() {
		return owner;
	}

	public void setOwner(UserInfo owner) {
		this.owner = owner;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<RelatedBO> getRelatedBos() {
		return relatedBos;
	}

	public void setRelatedBos(List<RelatedBO> relatedBos) {
		this.relatedBos = relatedBos;
	}

	public List<ParticipantInfo> getParticipants() {
		return participants;
	}

	public void setParticipants(List<ParticipantInfo> participants) {
		this.participants = participants;
	}

	public List<ActivityCustomerInfo> getActivityCustomers() {
		return activityCustomers;
	}

	public void setActivityCustomers(List<ActivityCustomerInfo> activityCustomers) {
		this.activityCustomers = activityCustomers;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public ActivityType getType() {
		return type;
	}

	public void setType(ActivityType type) {
		this.type = type;
	}

}
