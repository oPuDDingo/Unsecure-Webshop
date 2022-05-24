package backend.main.java.api;
import backend.main.java.Logic;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("login")public class LoginService
{
	@GET @Produces(MediaType.TEXT_PLAIN) public Response checkLogin(
		@DefaultValue("") @QueryParam("mail") String mail,
		@DefaultValue("") @QueryParam("password") String password
	) {
		return Logic.login(mail, password);
	}


}
