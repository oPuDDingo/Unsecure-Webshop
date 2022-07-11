package de.fhws.biedermann.webshop.api;


import de.fhws.biedermann.webshop.FlawHandler;
import de.fhws.biedermann.webshop.Logic;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("backup") public class BackupService
{
	@GET
	public Response getBackup(
		@HeaderParam( "uuid" ) final String uuid
	){
		FlawHandler.endpointScanning( uuid );
		return Response.ok( Logic.getDummyDataForInternApi()).build();
	}
}
