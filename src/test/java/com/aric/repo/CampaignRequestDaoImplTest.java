/**
 * 
 */
package com.aric.repo;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.aric.domain.Campaign;
import com.aric.domain.CampaignRequest;

/**
 * @author dursun
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml",
		"classpath:/META-INF/spring/applicationContext.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CampaignRequestDaoImplTest {
	
	@Autowired
	private CampaignRequestDao campaignRequestDao;

	/**
	 * Test method for
	 * {@link com.aric.repo.CampaignRequestDaoImpl#createCampaignRequest(com.aric.domain.Campaign, java.lang.Long, java.util.Date)}
	 * .
	 */
	@Test
	public void testCreateCampaignRequest() {
		
		Campaign campaign = new Campaign();
		campaign.setCampaignId(1l);
		campaign.setCampaignName("DUMMY");
		Long customerId = 1l;
		Date trxDate = new Date();
		CampaignRequest cr = campaignRequestDao.createCampaignRequest(campaign, customerId, trxDate);
		assertNotNull(cr);
		assertNotNull(cr.getCampaignRequestId());
	}

}
