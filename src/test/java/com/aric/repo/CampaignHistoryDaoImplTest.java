/**
 * 
 */
package com.aric.repo;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.aric.domain.CampaignHistory;
import com.aric.domain.enums.HistoryType;

/**
 * @author dursun
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml",
		"classpath:/META-INF/spring/applicationContext.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CampaignHistoryDaoImplTest {

	@Autowired
	private CampaignHistoryDao campaignHistoryDao;

	/**
	 * Test method for
	 * {@link com.aric.repo.CampaignHistoryDaoImpl#createHistory(com.aric.domain.enums.HistoryType, java.lang.Long, java.util.Date)}
	 * .
	 */
	@Test
	public void testCreateHistory() {
		CampaignHistory campaignHistory = campaignHistoryDao.createHistory(
				HistoryType.REQUEST_CREATED, 1l, new Date());
		Assert.assertNotNull(campaignHistory);
		Assert.assertNotNull(campaignHistory.getCampaignHistoryId());
	}

}
