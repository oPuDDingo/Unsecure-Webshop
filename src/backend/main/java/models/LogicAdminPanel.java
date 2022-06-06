package backend.main.java.models;

import backend.main.java.Logic;
import backend.main.java.database.DataAccessAdminPanel;

import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

public class LogicAdminPanel {
    DataAccessAdminPanel daap = new DataAccessAdminPanel();

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
}
