/**
 * 
 */
package com.aric.akka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;

import com.aric.service.CampaignRequestService;

/**
 * @author dursun
 * 
 */
@Service
public final class CampaignRequestActorFactory implements UntypedActorFactory {

	private static final long serialVersionUID = -4885332179067153448L;

	@Autowired
	private CampaignRequestService campaignRequestService;

	public UntypedActor create() {
		//TODO fixed parallel number is not a good solution
		return new CampaignRequestFetcher(campaignRequestService, 10);
	}
}
