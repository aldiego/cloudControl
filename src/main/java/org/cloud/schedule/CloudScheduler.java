package org.cloud.schedule;

import java.util.Set;

import org.cloud.service.ICloudService;
import org.jclouds.aws.domain.Region;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.ComputeServiceContext;
import org.jclouds.compute.domain.NodeMetadata;
import org.jclouds.ec2.EC2Client;
import org.jclouds.ec2.domain.PublicIpInstanceIdPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class CloudScheduler{
	protected final Logger logger = LoggerFactory.getLogger( CloudScheduler.class );
	
	@Autowired
	ComputeServiceContext computeContext;
	
	@Autowired
	ComputeService computeService;
	
	@Autowired
	ICloudService cloudService;
	
	@Value( "${cloud.machineId}" )
	String cloudMachineId;
	
	public CloudScheduler(){
		super();
	}
	
	// API
	
	@Scheduled( cron = "${beginSchedule}" )
	public final void beginSchedule(){
		logger.warn( "Beginning schedule for the machine: " + cloudMachineId );
		
		cloudService.start( cloudMachineId );
		reassociateElasticIp();
		
		logger.info( "Just begun schedule for the machine: " + cloudMachineId );
	}
	
	void reassociateElasticIp(){
		final EC2Client ec2Client = EC2Client.class.cast( computeContext.getProviderSpecificContext().getApi() );
		final Set< PublicIpInstanceIdPair > addressesInRegion = ec2Client.getElasticIPAddressServices().describeAddressesInRegion( Region.EU_WEST_1 );
		final PublicIpInstanceIdPair firstAvailableElasticIp = addressesInRegion.iterator().next();
		
		final NodeMetadata nodeMetadata = computeService.getNodeMetadata( cloudMachineId );
		ec2Client.getElasticIPAddressServices().associateAddressInRegion( Region.EU_WEST_1, firstAvailableElasticIp.getPublicIp(), nodeMetadata.getProviderId() );
	}
	
	@Scheduled( cron = "${endSchedule}" )
	public final void endSchedule(){
		logger.warn( "Ending schedule for the machine: " + cloudMachineId );
		
		cloudService.shutDown( cloudMachineId );
		
		logger.info( "Just ended schedule for the machine: " + cloudMachineId );
	}
	
}
