package org.cloud.service;

import org.junit.Test;
import org.junit.runner.RunWith;
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
public final class SpringContextTest{
	
	//
	@Test
	public final void whenContextIsInitialized_thenNoExceptions(){
		// Given
		
		// When
		
		// Then
	}
	
}
