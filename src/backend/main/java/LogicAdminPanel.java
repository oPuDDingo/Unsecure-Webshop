package backend.main.java;

import backend.main.java.Logic;
import backend.main.java.database.DataAccessAdminPanel;
import backend.main.java.database.DataAccessShopDatabase;

import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

public class LogicAdminPanel {
    private static DataAccessAdminPanel daap = new DataAccessAdminPanel();
    private static DataAccessShopDatabase dasd = new DataAccessShopDatabase();
    public static int level =1;

    public static Response login(final String username,final String password){
        if(daap.login(username, password)){
            String sessionId = Logic.createSessionId();
            daap.postSession(sessionId, username);
            return Response.ok(sessionId).header("sessionid", sessionId).build();
        }
        else{
            return Response.status(401).build();
        }
    }

    public static Response getRanking(String session){
        if(daap.checkSession(session)){
            return Response.ok(daap.getRanking()).build();
        }
        else{
            return Response.status(403).build();
        }
    }

    public static Response resetDatabaseShop (String session){
        if(daap.checkSession(session)){
            dasd.resetDatabase();
            return Response.ok().build();
        }
        else{
            return Response.status(403).build();
        }
    }

    //TODO: Valerie: Ich hab des hier gemacht, wenn was falsch ist bitte mitteilen
    public static Response getLevel(String session){
        if(daap.checkSession( session )){
            return Response.ok(level).build();
        }
        else{
            return Response.status(403).build();
        }
    }

    public static Response setLevel(String session, int levelModel){
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

    public static Response resetDatabaseRanking(String session){
        if(daap.checkSession(session)){
            daap.resetDatabase();
            return Response.ok().build();
        }
        else{
            return Response.status(403).build();
        }
    }
}
