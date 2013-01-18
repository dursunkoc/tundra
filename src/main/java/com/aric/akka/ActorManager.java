/**
 * 
 */
package com.aric.akka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;

import com.aric.service.CampaignRequestService;

/**
 * @author dursun
 * 
 */
@Service
public class ActorManager {
	@Autowired
	private CampaignRequestService campaignRequestService;

	private final ActorSystem system = ActorSystem.create("testSystem");
	private final UntypedActorFactory ACTOR_FACTORY = new ActorFactory(campaignRequestService);
	private final Props props = new Props(ACTOR_FACTORY);

	public final ActorRef newActor(String name) {
		ActorRef actorOfCampaignRequestWorker = system.actorOf(props, name);
		return actorOfCampaignRequestWorker;
	}

	public static final class ActorFactory implements UntypedActorFactory {
		private final CampaignRequestService campaignRequestService;

		public ActorFactory(final CampaignRequestService campaignRequestService) {
			this.campaignRequestService = campaignRequestService;
		}

		private static final long serialVersionUID = -4885332179067153448L;

		public UntypedActor create() {
			return new CampaignRequestWorker(campaignRequestService);
		}
	}

}
