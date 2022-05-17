package backend.main.java.api;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("login")public class LoginService
{
	@GET @Produces(MediaType.APPLICATION_JSON) public Response checkLogin(
		@DefaultValue("") @QueryParam("username") String username,
		@DefaultValue("") @QueryParam("password") String password
	) {
		// database check

		if (username.equals("admin") && password.equals("admin"))
		{
			return Response.ok("sessionID").build();
		} else {
			return Response.status(401).build();
		}
	}


}
