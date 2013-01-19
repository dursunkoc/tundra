/**
 * 
 */
package com.aric.akka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import com.aric.service.CampaignRequestService;

/**
 * @author dursun
 * 
 */
@Service
public class ActorManager {
	@Autowired
	private CampaignRequestService campaignRequestService;

	@Autowired
	private ActorSystem actorSystem;

	private Props props;

	/**
	 * @param campaignRequestActorFactory
	 */
	@Autowired
	public void setCampaignRequestActorFactory(
			CampaignRequestActorFactory campaignRequestActorFactory) {
		this.props = new Props(campaignRequestActorFactory);
	}

	/**
	 * @param name
	 * @return
	 */
	public final ActorRef newActor(String name) {
		ActorRef actorOfCampaignRequestWorker = actorSystem
				.actorOf(props, name);
		return actorOfCampaignRequestWorker;
	}

}
