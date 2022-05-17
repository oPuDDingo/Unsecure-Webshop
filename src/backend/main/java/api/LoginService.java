package backend.main.java.api;
import backend.main.java.Logic;

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

		String session = Logic.login(username, password);
		if (session == null)
		{
			return Response.status(404).build(); // login data not found
		}
		return Response.ok(session).build();
	}


}
