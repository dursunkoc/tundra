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
public class CampaignRequestProcessor extends UntypedActor {
	private final static Logger logger = LoggerFactory
			.getLogger(CampaignRequestFetcher.class);

	private final CampaignRequestService campaignRequestService;

	public CampaignRequestProcessor(
			CampaignRequestService campaignRequestService) {
		logger.info("Processor Created");
		this.campaignRequestService = campaignRequestService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see akka.actor.UntypedActor#onReceive(java.lang.Object)
	 */
	@Override
	public void onReceive(Object message) throws Exception {
		logger.info("Received a message: " + message);
		if (message instanceof ProcessWork) {
			ProcessWork pw = (ProcessWork) message;
			campaignRequestService.process(pw.getIdList());
		} else {
			Thread.sleep(1000);
			unhandled(message);
		}
	}

}
