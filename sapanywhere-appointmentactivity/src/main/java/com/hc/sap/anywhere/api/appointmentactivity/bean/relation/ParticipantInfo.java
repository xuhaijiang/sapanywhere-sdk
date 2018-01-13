package com.hc.sap.anywhere.api.appointmentactivity.bean.relation;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Appointment activity participant.
 * 
 * @author xuhaijiang
 *
 */
public class ParticipantInfo {
	
	@JsonProperty(value = "id")
    private long id;
	
	@JsonProperty(value = "participantType")
    private ParticipantType participantType;
	
	@JsonProperty(value = "participantId")
    private long participantId;

	public ParticipantInfo(){}
	
	public ParticipantInfo(ParticipantType participantType,long participantId){
		this.participantType = participantType;
		this.participantId = participantId;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ParticipantType getParticipantType() {
		return participantType;
	}

	public void setParticipantType(ParticipantType participantType) {
		this.participantType = participantType;
	}

	public long getParticipantId() {
		return participantId;
	}

	public void setParticipantId(long participantId) {
		this.participantId = participantId;
	}
	
	
}
