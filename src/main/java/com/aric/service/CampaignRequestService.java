/**
 * 
 */
package com.aric.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aric.domain.Campaign;
import com.aric.domain.CampaignRequest;
import com.aric.domain.enums.CampaignRequestStatus;
import com.aric.domain.enums.HistoryType;
import com.aric.repo.CampaignHistoryDao;
import com.aric.repo.CampaignRequestDao;

/**
 * @author dursun
 * 
 */
@Service
public class CampaignRequestService {
	@Autowired
	private CampaignRequestDao campaignRequestDao;

	@Autowired
	private CampaignHistoryDao campaignHistoryDao;

	@Transactional
	public CampaignRequest makeCampaignRequest(Campaign campaign,
			Long customerId, Date trxDate) {
		CampaignRequest campaignRequest = this.campaignRequestDao
				.createCampaignRequest(campaign, customerId, trxDate);
		this.campaignHistoryDao.createHistory(HistoryType.REQUEST_CREATED,
				campaignRequest.getCampaignRequestId(), trxDate);
		return campaignRequest;
	}

	@Transactional
	public void process(List<Long> idList) {
		for (Long id : idList) {
			processARecord(id);
		}
	}

	private void processARecord(Long id) {
		CampaignRequest campaignRequest = campaignRequestDao
				.findCampaignRequest(id);
		HistoryType historyType;
		if (CampaignRequestStatus.WAITING.equals(campaignRequest.getStatus())) {
			campaignRequest.setStatus(CampaignRequestStatus.OPERATING);
			historyType = HistoryType.REQUEST_PROCESSED;
		} else {
			campaignRequest.setStatus(CampaignRequestStatus.COMPLETED);
			historyType = HistoryType.REQUEST_COMPLETED;
		}
		campaignRequest = campaignRequestDao.update(campaignRequest);
		campaignHistoryDao.createHistory(historyType,
				campaignRequest.getCampaignRequestId(), new Date());
	}

	public List<Long> fetchForProcess() {
		List<Long> result = campaignRequestDao.getWaitingCampaignRequestIds();
		return result;
	}
}
