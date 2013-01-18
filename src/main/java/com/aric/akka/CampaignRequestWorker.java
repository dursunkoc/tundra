/**
 * 
 */
package com.aric.akka;

import akka.actor.UntypedActor;

import com.aric.service.CampaignRequestService;

/**
 * @author dursun
 *
 */
public class CampaignRequestWorker extends UntypedActor{

	private final CampaignRequestService campaignRequestService;
	
	public CampaignRequestWorker(CampaignRequestService campaignRequestService ) {
		this.campaignRequestService = campaignRequestService;
	}



	@Override
	public void onReceive(Object message) throws Exception {
		if(message instanceof ProcessWork){
			ProcessWork pw = (ProcessWork) message;
			campaignRequestService.process(pw.getIdList());
		}else{
			unhandled(message);
		}
	}
}
