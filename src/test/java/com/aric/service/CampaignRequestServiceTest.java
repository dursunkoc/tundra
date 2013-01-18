package com.aric.service;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.aric.domain.Campaign;
import com.aric.domain.CampaignRequest;
import com.aric.repo.CampaignHistoryDao;
import com.aric.repo.CampaignRequestDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml",
		"classpath:/META-INF/spring/applicationContext.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class CampaignRequestServiceTest {
	
	@Autowired
	private CampaignRequestService campaignRequestService;

	@Autowired
	private CampaignRequestDao campaignRequestDao;

	@Autowired
	private CampaignHistoryDao campaignHistoryDao;
	

	@Test
	public void testMakeCampaignRequest() throws Exception {
		Campaign campaign = new Campaign(1l,"DUMMY");
		Long customerId =1l;
		Date trxDate = new Date();
		CampaignRequest createdCampaignRequest = campaignRequestService.makeCampaignRequest(campaign, customerId, trxDate);
		Assert.assertNotNull(createdCampaignRequest);
		CampaignRequest foundCampaignRequest = campaignRequestDao.findCampaignRequest(createdCampaignRequest.getCampaignRequestId());
		Assert.assertNotNull(foundCampaignRequest);
	}

}
