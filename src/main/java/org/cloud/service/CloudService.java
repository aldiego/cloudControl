package org.cloud.service;

import org.jclouds.compute.ComputeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

@Service
public class CloudService implements ICloudService{
	protected final Logger logger = LoggerFactory.getLogger( CloudService.class );
	
	@Autowired
	ComputeService computeService;
	
	public CloudService(){
		super();
	}
	
	// API
	
	@Override
	public final void shutDown( final String vmId ){
		Preconditions.checkNotNull( vmId );
		
		logger.info( "Shutting down the machine: " + vmId );
		computeService.suspendNode( vmId );
	}
	
	@Override
	public final void start( String vmId ){
		Preconditions.checkNotNull( vmId );
		
		logger.info( "Starting the machine: " + vmId );
		computeService.resumeNode( vmId );
	}
	
}
