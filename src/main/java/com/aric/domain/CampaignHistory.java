/**
 * 
 */
package com.aric.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.aric.domain.enums.HistoryType;

/**
 * @author dursun
 * 
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "historyType",
		"campaignRequestId" }) })
public class CampaignHistory {

	@Id
	@GeneratedValue
	private Long campaignHistoryId;
	
	@NotNull
	@Column
	@Enumerated(EnumType.ORDINAL)
	private HistoryType historyType;
	
	@NotNull
	@Column
	private Long campaignRequestId;
	
	@NotNull
	@Column
	private Date trxDate;
	

	public CampaignHistory(HistoryType historyType, Long campaignRequestId,
			Date trxDate) {
		this.historyType = historyType;
		this.campaignRequestId = campaignRequestId;
		this.trxDate = trxDate;
	}
	
	public CampaignHistory() {
	}

	public Long getCampaignHistoryId() {
		return campaignHistoryId;
	}

	public void setCampaignHistoryId(Long campaignHistoryId) {
		this.campaignHistoryId = campaignHistoryId;
	}

	public HistoryType getHistoryType() {
		return historyType;
	}

	public void setHistoryType(HistoryType historyType) {
		this.historyType = historyType;
	}

	public Long getCampaignRequestId() {
		return campaignRequestId;
	}

	public void setCampaignRequestId(Long campaignRequestId) {
		this.campaignRequestId = campaignRequestId;
	}

	public Date getTrxDate() {
		return trxDate;
	}

	public void setTrxDate(Date trxDate) {
		this.trxDate = trxDate;
	}

}
