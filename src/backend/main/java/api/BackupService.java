package backend.main.java.api;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("backup") public class BackupService
{
	// secret endpoint. its a vulnerability
	@GET
	public Response getBackup() {
		return Response.ok("You found it!").build();
	}
}
