/**
 * 
 */
package com.aric.akka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.UntypedActor;

import com.aric.service.CampaignRequestService;

/**
 * @author dursun
 * 
 */
public class CampaignRequestWorker extends UntypedActor {
	private final static Logger logger = LoggerFactory
			.getLogger(CampaignRequestWorker.class);

	private final CampaignRequestService campaignRequestService;

	public CampaignRequestWorker(CampaignRequestService campaignRequestService) {
		this.campaignRequestService = campaignRequestService;
	}

	@Override
	public void onReceive(Object message) throws Exception {
		Thread.sleep(3000);
		if (message instanceof ProcessWork) {
			ProcessWork pw = (ProcessWork) message;
			campaignRequestService.process(pw.getIdList());
		} else if (message instanceof String) {
			logger.info("Received message is: " + message);
			System.out.println("Received message is: " + message);
		} else {
			unhandled(message);
		}
	}
}
