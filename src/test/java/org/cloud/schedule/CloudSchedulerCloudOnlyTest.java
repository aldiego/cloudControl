package org.cloud.schedule;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = {//@formatter:off
	"classpath:/cloud_control_config.xml",
	
	"classpath:/cloud_control_scan.xml"
} )// @formatter:on
public final class CloudSchedulerCloudOnlyTest{
	
	@Autowired
	CloudScheduler instance;
	
	// tests
	
	@Test
	@Ignore
	public final void whenElasticIpAssociationIsTriggered_thenNoExceptions(){
		instance.reassociateElasticIp();
	}
	
	@Test
	public final void whenScheduleBegins_thenNoExceptions(){
		instance.beginSchedule();
	}
	
	@Test
	@Ignore
	public final void whenScheduleEnds_thenNoExceptions(){
		instance.endSchedule();
	}
	
}
