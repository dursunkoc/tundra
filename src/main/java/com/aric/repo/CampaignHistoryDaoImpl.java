/**
 * 
 */
package com.aric.repo;

import java.util.Date;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aric.domain.CampaignHistory;
import com.aric.domain.enums.HistoryType;

/**
 * @author dursun
 * 
 */
@Repository
public class CampaignHistoryDaoImpl implements CampaignHistoryDao {

	@Autowired
	private EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.repo.CampaignHistoryDao#createHistory(com.aric.domain.enums.
	 * HistoryType, java.lang.Long, java.util.Date)
	 */
	@Override
	public CampaignHistory createHistory(HistoryType historyType,
			Long campaignRequestId, Date trxDate) {
		CampaignHistory campaignHistory = new CampaignHistory(historyType,
				campaignRequestId, trxDate);
		em.persist(campaignHistory);
		return campaignHistory;
	}

}
