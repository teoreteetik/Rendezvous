package ee.teoreteetik.tt.client.endpoint.socket;

import ee.teoreteetik.tt.internal.model.Topic;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/topicsocket")
public class TopicSocket {

  private static Set<Session> activeSockets = new HashSet<>();

  @OnOpen
  public void open(Session session) {
    activeSockets.add(session);
  }

  @OnClose
  public void onClose(Session session, CloseReason closeReason) {
    activeSockets.remove(session);
  }

  public static void sendTopicAlert(Topic topic) {
    String message = "Uus teema '" + topic.getTitle() + "'!";
    for (Session session : activeSockets) {
      try {
        session.getBasicRemote().sendText(message);
      } catch (IOException e) {
        e.printStackTrace();
        // TODO
      }
    }
  }
}
