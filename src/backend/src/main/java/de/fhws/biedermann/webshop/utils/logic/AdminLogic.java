package de.fhws.biedermann.webshop.utils.logic;

import de.fhws.biedermann.webshop.database.DataAccessAdminPanel;
import de.fhws.biedermann.webshop.database.DataAccessShopDatabase;

import javax.ws.rs.core.Response;

public class AdminLogic
{

    private static AdminLogic INSTANCE;

    private final DataAccessAdminPanel daap;
    private final DataAccessShopDatabase dasd;
    public int level;

    private AdminLogic(final int level) {
        this.daap = new DataAccessAdminPanel();
        this.dasd = new DataAccessShopDatabase();
        this.level = level;
    }

    public static AdminLogic getInstance( )
    {
        if ( INSTANCE == null )
        {
            INSTANCE = new AdminLogic( 1 );
        }

        return INSTANCE;
    }

    public Response login(final String username,final String password){
        if(daap.login(username, password)){
            String sessionId = AuthenticationLogic.createSessionId();
            daap.postSession(sessionId, username);
            return Response.ok(sessionId).header("sessionid", sessionId).build();
        }
        else{
            return Response.status(401).build();
        }
    }

    public Response logout(final String session){
        daap.deleteSession( session );
        return Response.ok().build();
    }

    public Response getRanking(String session){
        if(daap.checkSession(session)){
            return Response.ok(daap.getRanking()).build();
        }
        else{
            return Response.status(403).build();
        }
    }

    public Response resetDatabaseShop (String session){
        if(daap.checkSession(session)){
            dasd.resetDatabase();
            return Response.ok().build();
        }
        else{
            return Response.status(403).build();
        }
    }

    public Response getLevel(String session){
        if(daap.checkSession( session )){
            return Response.ok(level).build();
        }
        else{
            return Response.status(403).build();
        }
    }

    public Response setLevel(String session, int levelModel){
        if(daap.checkSession(session)){
            if(levelModel >=1 && levelModel <=3){
                level=levelModel;
                return Response.ok().build();
            }
            else{
                return Response.status(406).build();
            }
        }
        else{
            return Response.status(403).build();
        }
    }

    public Response resetDatabaseRanking(String session){
        if(daap.checkSession(session)){
            daap.resetDatabase();
            return Response.ok().build();
        }
        else{
            return Response.status(403).build();
        }
    }
}
