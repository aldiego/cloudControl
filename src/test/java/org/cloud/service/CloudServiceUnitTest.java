package org.cloud.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.jclouds.compute.ComputeService;
import org.junit.Before;
import org.junit.Test;

/**
 * @author eugenp
 */
public final class CloudServiceUnitTest{
	
	CloudService cloudService;
	
	@Before
	public final void before(){
		cloudService = new CloudService();
		cloudService.computeService = mock( ComputeService.class );
	}
	
	//
	
	@Test
	public final void whenVMShutDownIsTriggered_thenTheVMIsShutDown(){
		final String machineId = "";
		// Given
		
		// When
		cloudService.shutDown( machineId );
		
		// Then
		verify( cloudService.computeService ).suspendNode( machineId );
	}
	
	@Test
	public final void whenVMStartIsTriggered_thenTheVMIsStarted(){
		final String machineId = "";
		// Given
		
		// When
		cloudService.start( machineId );
		
		// Then
		verify( cloudService.computeService ).resumeNode( machineId );
	}
	
}
