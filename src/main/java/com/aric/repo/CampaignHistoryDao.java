/**
 * 
 */
package com.aric.repo;

import java.util.Date;

import com.aric.domain.CampaignHistory;
import com.aric.domain.enums.HistoryType;

/**
 * @author dursun
 * 
 */
public interface CampaignHistoryDao {
	public CampaignHistory createHistory(HistoryType historyType, Long campaignRequestId,
			Date trxDate);
}
