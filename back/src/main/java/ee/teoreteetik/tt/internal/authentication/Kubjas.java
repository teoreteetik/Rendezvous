package ee.teoreteetik.tt.internal.authentication;

import ee.teoreteetik.tt.model.User;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Kubjas {
  private static Map<String, AuthToken> activeTokens = new HashMap<>();

  private static void add(String uuid, AuthToken auth) {
    activeTokens.put(uuid, auth);
  }

  private static AuthToken getToken(String uuid) {
    return activeTokens.get(uuid);
  }

  private static void invalidate(String uuid) {
    activeTokens.remove(uuid);
  }

  public static AuthToken login(User user) {
    AuthToken token = new AuthToken();
    token.setUser(user);
    token.setUuid(UUID.randomUUID().toString());
    add(token.getUuid(), token);
    return token;
  }

  public static void logout(String token) {
    invalidate(token);
  }

  public static User getUser(String uuid) {
    AuthToken token = getToken(uuid);
    if (token != null) {
      return token.getUser();
    }
    return null;
  }
}
