package backend.main.java.api;

import backend.main.java.database.DataAccessAdminPanel;
import backend.main.java.models.LogicAdminPanel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("admin")
public class AdminService {

    LogicAdminPanel lap = new LogicAdminPanel();

    @GET
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response checkLogin(@DefaultValue("") @QueryParam("username") String username,
                               @DefaultValue("") @QueryParam("password") String password){

        return lap.login(username, password);
    }
}
