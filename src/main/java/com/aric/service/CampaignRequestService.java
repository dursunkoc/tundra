/**
 * 
 */
package com.aric.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aric.domain.CampaignHistory;
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
	private CampaignHistory campaignHistory;
	
	public void process(List<Long> idList){
		
	}
}
