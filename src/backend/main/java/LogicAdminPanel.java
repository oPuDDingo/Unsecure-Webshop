package backend.main.java;

import backend.main.java.database.DataAccessAdminPanel;
import backend.main.java.database.DataAccessShopDatabase;
import javax.ws.rs.core.Response;

public class LogicAdminPanel {

    private static LogicAdminPanel INSTANCE;

    private final DataAccessAdminPanel daap;
    private final DataAccessShopDatabase dasd;
    public int level;

    private LogicAdminPanel(final int level) {
        this.daap = new DataAccessAdminPanel();
        this.dasd = new DataAccessShopDatabase();
        this.level = level;
    }

    public static final LogicAdminPanel getInstance( )
    {
        if ( INSTANCE == null )
        {
            INSTANCE = new LogicAdminPanel( 1 );
        }

        return INSTANCE;
    }

    public Response login(final String username,final String password){
        if(daap.login(username, password)){
            String sessionId = Logic.createSessionId();
            daap.postSession(sessionId, username);
            return Response.ok(sessionId).header("sessionid", sessionId).build();
        }
        else{
            return Response.status(401).build();
        }
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

    //TODO: Valerie: Ich hab des hier gemacht, wenn was falsch ist bitte mitteilen
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
