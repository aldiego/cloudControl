package org.cloud.factory;

import static org.mockito.Mockito.mock;

import org.cloud.util.CloudConstants;
import org.jclouds.compute.ComputeServiceContext;
import org.jclouds.compute.ComputeServiceContextFactory;
import org.jclouds.logging.log4j.config.Log4JLoggingModule;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Module;

@Component
final class MyComputeServiceContextFactory implements FactoryBean< ComputeServiceContext >{
	
	@Value( "${cloud.active}" )
	boolean cloudActive;
	
	// API
	
	@Override
	public final ComputeServiceContext getObject(){
		final ComputeServiceContext context;
		
		if( cloudActive ){
			context = new ComputeServiceContextFactory().createContext( CloudConstants.PROVIDER, CloudConstants.ACCESS_KEY_ID, CloudConstants.SECRET_KEY, ImmutableSet.<Module> of( new Log4JLoggingModule() ) );
		}
		else{
			context = mock( ComputeServiceContext.class );
		}
		
		return context;
	}
	
	@Override
	public final Class< ComputeServiceContext > getObjectType(){
		return ComputeServiceContext.class;
	}
	@Override
	public final boolean isSingleton(){
		return true;
	}
	
}
