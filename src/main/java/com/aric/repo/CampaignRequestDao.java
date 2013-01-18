/**
 * 
 */
package com.aric.repo;

import java.util.Date;

import com.aric.domain.Campaign;
import com.aric.domain.CampaignRequest;

/**
 * @author dursun
 * 
 */
public interface CampaignRequestDao {

	/**
	 * @param campaign
	 * @param customerId
	 * @param trxDate
	 * @return
	 */
	public CampaignRequest createCampaignRequest(Campaign campaign,
			Long customerId, Date trxDate);
}
