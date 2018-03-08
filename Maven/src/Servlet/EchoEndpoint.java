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
		System.out.println("session openend and bound to room: " + room);
	}
		
	@OnMessage
	public void echoTextMessage(Session session, String msg) {
    	synchronized (clients){
    		for (Session client: clients)
				try {
					client.getBasicRemote().sendText(msg);
				} catch (IOException e) {}
        }
	}

}
	
