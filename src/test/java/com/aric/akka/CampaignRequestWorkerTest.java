package com.aric.akka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml",
		"classpath:/META-INF/spring/applicationContext.xml" })
public class CampaignRequestWorkerTest {
	@Autowired
	private ActorManager actorManager;

	@Test
	public void testOnReceiveObject() throws InterruptedException {
		actorManager.newActor("test0").tell("hello0", null);
		actorManager.newActor("test1").tell("hello1", null);
		actorManager.newActor("test2").tell("hello2", null);
//		Thread.sleep(4000);
	}

}
