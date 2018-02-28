package Servlet;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/echo/{room}")
public class EchoEndpoint {

private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>()); 
	
@OnOpen
public void open(Session session, @PathParam("room") String room) {
	clients.add(session);
}
	
@OnMessage
public void echoTextMessage(Session session, String msg) {
    try {
    	synchronized (clients){
    		for (Session client: clients) 
				client.getBasicRemote().sendText(msg);
        }
    } catch (IOException e) {
    	System.out.println("Can't send Message!");
        try {
            session.close();
        } catch (IOException e1){}
    }
}


}
	
