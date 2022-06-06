package backend.main.java.api;

import backend.main.java.LogicAdminPanel;

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

    @GET
    @Path("interface")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRanking(@CookieParam("sessionID") String session){
        return lap.getRanking(session);
    }

    @PUT
    @Path("interface")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response resetDatabaseShop(@CookieParam("sessionID") String session){
        return lap.resetDatabaseShop(session);
    }

    @POST
    @Path("interface")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setLevel(@CookieParam("sessionID") String session, @DefaultValue("1") @QueryParam("level") int level){
        return lap.setLevel(session, level);
    }
}
