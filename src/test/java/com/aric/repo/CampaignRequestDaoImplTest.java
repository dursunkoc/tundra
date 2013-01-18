/**
 * 
 */
package com.aric.repo;

import static org.junit.Assert.assertEquals;
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
import org.springframework.transaction.annotation.Transactional;

import com.aric.domain.Campaign;
import com.aric.domain.CampaignRequest;
import com.aric.domain.enums.CampaignRequestStatus;

/**
 * @author dursun
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml",
		"classpath:/META-INF/spring/applicationContext.xml" })
// @Transactional
// @TransactionConfiguration(defaultRollback = false)
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

		CampaignRequest cr = campaignRequestDao.createCampaignRequest(
				new Campaign(1l, "DUMMY"), 1l, new Date());
		assertNotNull(cr);
		assertNotNull(cr.getCampaignRequestId());
	}

	@Test
	@Transactional
	public void testFindCampaignRequest() throws Exception {
		CampaignRequest result = campaignRequestDao.findCampaignRequest(1l);
		assertNotNull(result);
		logger.info("Main Query Completed");
		assertNotNull(result.getCampaign().getCampaignId());
	}

	@Test
	@Transactional
	public void testUpdateWithTrx() throws Exception {
		CampaignRequest result = campaignRequestDao.createCampaignRequest(
				new Campaign(1l, "DUMMY"), 1l, new Date());
		result.setStatus(CampaignRequestStatus.OPERATING);
		
		result = campaignRequestDao.findCampaignRequest(result.getCampaignRequestId());
		assertEquals(result.getStatus(), CampaignRequestStatus.OPERATING);
		
		result.setStatus(CampaignRequestStatus.OPERATING);
		campaignRequestDao.update(result);
		
		result = campaignRequestDao.findCampaignRequest(result.getCampaignRequestId());
		assertEquals(CampaignRequestStatus.OPERATING, result.getStatus());
	}
	@Test
	public void testUpdateWithoutTrx() throws Exception {
		CampaignRequest result = campaignRequestDao.createCampaignRequest(
				new Campaign(1l, "DUMMY"), 1l, new Date());
		
		result.setStatus(CampaignRequestStatus.OPERATING);
		
		result = campaignRequestDao.findCampaignRequest(result.getCampaignRequestId());
		assertEquals(result.getStatus(), CampaignRequestStatus.WAITING);
		
		result.setStatus(CampaignRequestStatus.OPERATING);
		campaignRequestDao.update(result);
		
		result = campaignRequestDao.findCampaignRequest(result.getCampaignRequestId());
		assertEquals(CampaignRequestStatus.OPERATING, result.getStatus());
	}

}
