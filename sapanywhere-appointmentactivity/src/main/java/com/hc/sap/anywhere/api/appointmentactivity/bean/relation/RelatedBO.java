package com.hc.sap.anywhere.api.appointmentactivity.bean.relation;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Appointment activity related business object.
 * 
 * @author xuhaijiang
 *
 */
public class RelatedBO {

	@JsonProperty(value = "id")
	private long id;

	@JsonProperty(value = "relatedBoType")
	private RelatedBoType relatedBoType;

	@JsonProperty(value = "relatedBoId")
	private long relatedBoId;

	public RelatedBO() {
	}

	public RelatedBO(RelatedBoType relatedBoType, long relatedBoId) {
		this.relatedBoType = relatedBoType;
		this.relatedBoId = relatedBoId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RelatedBoType getRelatedBoType() {
		return relatedBoType;
	}

	public void setRelatedBoType(RelatedBoType relatedBoType) {
		this.relatedBoType = relatedBoType;
	}

	public long getRelatedBoId() {
		return relatedBoId;
	}

	public void setRelatedBoId(long relatedBoId) {
		this.relatedBoId = relatedBoId;
	}

}
