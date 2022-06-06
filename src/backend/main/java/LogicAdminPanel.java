package backend.main.java;

import backend.main.java.Logic;
import backend.main.java.database.DataAccessAdminPanel;
import backend.main.java.database.DataAccessShopDatabase;

import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

public class LogicAdminPanel {
    DataAccessAdminPanel daap = new DataAccessAdminPanel();
    DataAccessShopDatabase dasd = new DataAccessShopDatabase();

    public Response login(final String username,final String password){
        if(daap.login(username, password)){
            String sessionId = Logic.createSessionId();
            daap.postSession(sessionId, username);
            return Response.ok(sessionId).cookie(new NewCookie("sessionID", sessionId)).build();
        }
        else{
            return null;
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
}
