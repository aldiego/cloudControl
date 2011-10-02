package org.cloud.factory;

import static org.mockito.Mockito.mock;

import org.jclouds.compute.ComputeService;
import org.jclouds.compute.ComputeServiceContext;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
final class MyComputeServiceFactory implements FactoryBean< ComputeService >{
	
	@Autowired
	ComputeServiceContext computeServiceContext;
	
	@Value( "${cloud.active}" )
	boolean cloudActive;
	
	// API
	
	@Override
	public final ComputeService getObject(){
		final ComputeService computeService;
		
		if( cloudActive ){
			computeService = computeServiceContext.getComputeService();
		}
		else{
			computeService = mock( ComputeService.class );
		}
		
		return computeService;
	}
	
	@Override
	public final Class< ComputeService > getObjectType(){
		return ComputeService.class;
	}
	@Override
	public final boolean isSingleton(){
		return true;
	}
	
}
