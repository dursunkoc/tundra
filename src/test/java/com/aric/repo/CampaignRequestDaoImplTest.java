/**
 * 
 */
package com.aric.repo;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
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
@TransactionConfiguration(defaultRollback = false)
public class CampaignRequestDaoImplTest {

	private static final Logger logger = LoggerFactory
			.getLogger(CampaignRequestDaoImplTest.class);

	@Autowired
	private CampaignRequestDao campaignRequestDao;

	/**
	 * Test method for
	 * {@link com.aric.repo.CampaignRequestDaoImpl#createCampaignRequest(com.aric.domain.Campaign, java.lang.Long, java.util.Date)}
	 * .
	 */
	@Test
	@Rollback(false)
	public void testCreateCampaignRequest() {

		Campaign campaign = new Campaign();
		campaign.setCampaignId(1l);
		campaign.setCampaignName("DUMMY");
		Long customerId = 1l;
		Date trxDate = new Date();
		CampaignRequest cr = campaignRequestDao.createCampaignRequest(campaign,
				customerId, trxDate);
		assertNotNull(cr);
		assertNotNull(cr.getCampaignRequestId());
	}

	@Test
	public void testFindCampaignRequest() throws Exception {
		CampaignRequest result = campaignRequestDao.findCampaignRequest(1l);
		assertNotNull(result);
		logger.info("Main Query Completed");
		assertNotNull(result.getCampaign().getCampaignId());
	}

}
