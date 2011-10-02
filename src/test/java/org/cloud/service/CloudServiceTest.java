package org.cloud.service;

import static org.mockito.Mockito.reset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.exceptions.misusing.NotAMockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author eugenp
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = {//@formatter:off
	"classpath:/cloud_control_config.xml",
	
	"classpath:/cloud_control_scan.xml"
} )// @formatter:on
public final class CloudServiceTest{
	
	@Value( "${cloud.machineId}" )
	String cloudMachineId;
	
	@Autowired
	CloudService cloudService;
	
	@Before
	public final void before(){
		try{
			reset( cloudService.computeService );
		}
		catch( final NotAMockException e ){
			// OK because this tests runs in the contexts - when the interaction with the cloud is real and when it's mocked
		}
	}
	
	// start
	
	@Test( expected = NullPointerException.class )
	public final void whenNullVMStartIsTriggered_thenExceptionIsThrown(){
		cloudService.start( null );
	}
	
	@Test
	public final void whenVMStartIsTriggered_thenNoException(){
		cloudService.start( cloudMachineId );
	}
	
	// shut down
	
	@Test( expected = NullPointerException.class )
	public final void whenNullVMShutDownIsTriggered_thenExceptionIsThrown(){
		// Given
		
		// When
		cloudService.shutDown( null );
		
		// Then
	}
	
	@Test
	public final void whenVMShutDownIsTriggered_thenNoException(){
		// Given
		
		// When
		cloudService.shutDown( cloudMachineId );
		
		// Then
	}
	
}
