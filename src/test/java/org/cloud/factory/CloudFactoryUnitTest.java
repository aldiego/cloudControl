package org.cloud.factory;

import org.junit.Before;
import org.junit.Test;

public final class CloudFactoryUnitTest{
	MyComputeServiceContextFactory instance;
	
	// fixture
	
	@Before
	public final void before(){
		instance = new MyComputeServiceContextFactory();
	}
	
	// tests
	
	@Test
	public void givenCloudIsNonActive_whenFactoryCreatesBean_thenNoExceptions(){
		// Given
		instance.cloudActive = false;
		
		// When
		instance.getObject();
	}
	
}
