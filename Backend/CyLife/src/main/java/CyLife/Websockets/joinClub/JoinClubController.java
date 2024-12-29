package CyLife.Websockets.joinClub;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@ServerEndpoint(value = "/joinClub/{clubId}/{name}")

public class JoinClubController {

    private static JoinClubMessageRepository msgRepo;

    @Autowired
    public void setMessageRepository(JoinClubMessageRepository repo) {
        msgRepo = repo;
        
    }

    private static Map<Session, String> sessionNameMap = new Hashtable<>();
    private static Map<String, Session> nameSessionMap = new Hashtable<>();

    private static Map<String, Map<Session, String>> clubSessionMap = new Hashtable<>();

    private final Logger logger = LoggerFactory.getLogger(JoinClubController.class);

    public static Map<Session, String> getSessionNameMap() {
        return sessionNameMap;
    }
    public static Map<String, Session> getNameSessionMap() {
        return nameSessionMap;
    }

    public static void setSessionNameMap(Map<Session, String> sessionNameMap) {
        JoinClubController.sessionNameMap = sessionNameMap;
    }
    public static void setNameSessionMap(Map<String, Session> nameSessionMap) {
        JoinClubController.nameSessionMap = nameSessionMap;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("clubId") String clubId, @PathParam("name") String name)
            throws IOException {
        logger.info("Entered into Open for club: " + clubId);

        clubSessionMap.computeIfAbsent(clubId, k -> new Hashtable<>());

        clubSessionMap.get(clubId).put(session, name);

        sessionNameMap.put(session, name);
        nameSessionMap.put(name, session);

        if (name.equals(clubId)) {
            logger.info("Getting chat history");
            String chatHistory = getChatHistory(clubId);
            session.getBasicRemote().sendText(chatHistory);
            return;
        }

        String message = name + " has joined the club!";
        broadcastToClub(clubId, message);

        JoinClubMessage msg = new JoinClubMessage(clubId, name, message);
        msgRepo.save(msg);
        logger.info("Message saved to repository: " + message);

        session.close();
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        logger.info("Entered into Close");

        String username = sessionNameMap.get(session);
        sessionNameMap.remove(session);
        nameSessionMap.remove(username);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        
        logger.info("Entered into Error");
        throwable.printStackTrace();
    }

    private void broadcastToClub(String clubId, String message) {
        Map<Session, String> sessionNameMap = clubSessionMap.get(clubId);

        if (sessionNameMap != null) {
            sessionNameMap.forEach((session, username) -> {
                try {
                    session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    logger.error("Exception: " + e.getMessage(), e);
                }
            });
        }
    }

    private String getChatHistory(String clubId) {
        List<JoinClubMessage> messages = msgRepo.findByClubId(clubId);

        if (messages.isEmpty()) {
            logger.info("No chat history found.");
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (JoinClubMessage message : messages) {
            sb.append(message.getContent()).append("\n");
        }

        String history = sb.toString();
        logger.info("Chat History:\n" + history);
        return history;
    }
}