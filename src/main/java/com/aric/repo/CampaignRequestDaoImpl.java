/**
 * 
 */
package com.aric.repo;

import java.util.Date;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aric.domain.Campaign;
import com.aric.domain.CampaignRequest;
import com.aric.domain.enums.CampaignRequestStatus;
import com.aric.domain.enums.HistoryType;

/**
 * @author dursun
 * 
 */
@Repository
@Transactional
public class CampaignRequestDaoImpl implements CampaignRequestDao {

	@Autowired
	private EntityManager em;

	@Autowired
	private CampaignHistoryDao campaignHistoryDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aric.repo.CampaignRequestDao#createCampaignRequest()
	 */
	@Override
	public CampaignRequest createCampaignRequest(Campaign campaign,
			Long customerId, Date trxDate) {
		CampaignRequest campaignRequest = new CampaignRequest(campaign,
				customerId, CampaignRequestStatus.WAITING);
		this.em.persist(campaignRequest);
		this.campaignHistoryDao.createHistory(HistoryType.REQUEST_CREATED,
				campaignRequest.getCampaignRequestId(), trxDate);
		return campaignRequest;
	}

}
