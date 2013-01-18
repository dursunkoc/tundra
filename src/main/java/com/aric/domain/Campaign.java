/**
 * 
 */
package com.aric.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author dursun
 * 
 */
@Entity
@Table
public class Campaign {
	@Id
	@GeneratedValue
	private Long campaignId;

	@NotNull
	@Column
	@Size(min = 3, max = 120)
	private String campaignName;

	public Long getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

}
