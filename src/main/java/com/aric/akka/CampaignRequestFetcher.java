/**
 * 
 */
package com.aric.akka;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinRouter;

import com.aric.service.CampaignRequestService;
import com.aric.utils.ProcessUtils;

/**
 * @author dursun
 * 
 */
public class CampaignRequestFetcher extends UntypedActor {
	private final static Logger logger = LoggerFactory
			.getLogger(CampaignRequestFetcher.class);

	private final CampaignRequestService campaignRequestService;
	private Integer numberOfParallelProcessors;

	private ActorRef campaignRequestProcessorRouter;

	public CampaignRequestFetcher(
			CampaignRequestService campaignRequestService,
			Integer numberOfParallelProcessors) {
		logger.info("Fetcher Created");
		this.campaignRequestService = campaignRequestService;
		this.numberOfParallelProcessors = numberOfParallelProcessors;
		this.campaignRequestProcessorRouter = this
				.getContext()
				.actorOf(
						new Props(CampaignRequestProcessor.class).withRouter(new RoundRobinRouter(
								this.numberOfParallelProcessors)),
						"campaignRequestProcessorRouter");
	}

	@Override
	public void onReceive(Object message) throws Exception {
		logger.info("Fetcher Received a message: " + message);
		if (message instanceof FetchWork) {
			List<Long> ids = campaignRequestService.fetchForProcess();

			ProcessWork[] idBatches = ProcessUtils.partitionToBatches(ids,
					numberOfParallelProcessors);

			for (ProcessWork pw : idBatches) {
				campaignRequestProcessorRouter.tell(pw, getSelf());
			}

		} else {
			Thread.sleep(1000);
			unhandled(message);
		}
	}

	@Override
	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append("CampaignRequestFetcher [");
		b.append("numberOfParallelProcessors: ");
		b.append(this.numberOfParallelProcessors);
		b.append("]");
		return super.toString();
	}
}
