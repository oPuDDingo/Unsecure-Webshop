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
            return Response.ok(sessionId).cookie(new NewCookie("sessionID", sessionId)).build();
        }
        else{
            return null;
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

    public static Response setLevel(String session, int level){
        if(daap.checkSession(session)){
            if(level >=1 && level <=3){
                level=level;
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
