package org.cloud.service;

public interface ICloudService{
	
	void shutDown( final String vmId );
	
	void start( final String vmId );
	
}
