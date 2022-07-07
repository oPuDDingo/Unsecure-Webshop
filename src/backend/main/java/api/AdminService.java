package backend.main.java.api;

import backend.main.java.LogicAdminPanel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("admin")
public class AdminService {

    @GET
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response checkLogin(@DefaultValue("") @QueryParam("username") String username,
                               @DefaultValue("") @QueryParam("password") String password){

        return LogicAdminPanel.getInstance().login(username, password);
    }

    @GET
    @Path("interface")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRanking(@HeaderParam("sessionid") String session){
        return LogicAdminPanel.getInstance().getRanking(session);
    }

    @GET
    @Path( "level" )
    public Response getLevel(@HeaderParam( "sessionid" ) String session) {
        return LogicAdminPanel.getInstance().getLevel(session);
    }

    @PUT
    @Path("interface")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response resetDatabaseShop(@HeaderParam("sessionid") String session){
        return LogicAdminPanel.getInstance().resetDatabaseShop(session);
    }

    @POST
    @Path("logout")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logout(
        @HeaderParam("sessionid") String session
    ){
        return LogicAdminPanel.getInstance().logout( session );
    }

    @POST
    @Path("interface")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setLevel(
        @HeaderParam("sessionid") String session,
        @DefaultValue("1") @QueryParam("level") int level
    ){
        return LogicAdminPanel.getInstance().setLevel(session, level);
    }

    @DELETE
    @Path("interface")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response resetDatabaseRanking(@HeaderParam("sessionid") String session){
        return LogicAdminPanel.getInstance().resetDatabaseRanking(session);
    }
}
