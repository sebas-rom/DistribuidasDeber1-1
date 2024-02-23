package com.example;

import jakarta.websocket.CloseReason;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/userWebSocket")
public class UserWebSocketEndpoint {

    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        // Add the new session to the set
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        // Remove the closed session from the set
        sessions.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public static void notifyClientsAboutNewUser(String username) {
        // Broadcast the new user to all connected clients
        for (Session session : sessions) {
            try {
                session.getBasicRemote().sendText(username);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}