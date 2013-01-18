/**
 * 
 */
package com.aric.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.aric.domain.enums.CampaignRequestStatus;

/**
 * @author dursun
 * 
 */
@Entity
@Table
public class CampaignRequest {

	@Id
	@GeneratedValue
	private Long campaignRequestId;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Column
	private CampaignRequestStatus status;
	
	@NotNull
	@Column
	private Long customerId;
	
	@ManyToOne(optional=false)
	private Campaign campaign;
	
	public CampaignRequest() {
	}

	public CampaignRequest(Campaign campaign, Long customerId,
			CampaignRequestStatus status) {
		this.campaign = campaign;
		this.customerId = customerId;
		this.status = status;
	}

	public Long getCampaignRequestId() {
		return campaignRequestId;
	}

	public void setCampaignRequestId(Long campaignRequestId) {
		this.campaignRequestId = campaignRequestId;
	}

	public CampaignRequestStatus getStatus() {
		return status;
	}

	public void setStatus(CampaignRequestStatus status) {
		this.status = status;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}
	
	
	

}
