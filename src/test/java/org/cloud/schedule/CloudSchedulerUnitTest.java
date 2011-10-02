package org.cloud.schedule;

import static org.mockito.Mockito.mock;

import org.cloud.service.ICloudService;
import org.jclouds.compute.ComputeServiceContext;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.stubbing.defaultanswers.ReturnsMocks;

public final class CloudSchedulerUnitTest{
	CloudScheduler instance;
	
	@Before
	public final void before(){
		instance = mock( CloudScheduler.class );
		instance.cloudService = mock( ICloudService.class );
		instance.computeContext = mock( ComputeServiceContext.class, new ReturnsMocks() );
	}
	
	// tests
	
	@Test
	public final void whenScheduleBegins_thenNoExceptions(){
		instance.beginSchedule();
	}
	
	@Test
	public final void whenScheduleEnds_thenNoExceptions(){
		instance.endSchedule();
	}
	
}
