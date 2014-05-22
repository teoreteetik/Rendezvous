package ee.teoreteetik.tt.internal.authentication.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.teoreteetik.tt.internal.authentication.GoogleTokenResponse;
import ee.teoreteetik.tt.internal.authentication.Privilege;
import ee.teoreteetik.tt.internal.authentication.service.AuthService;
import ee.teoreteetik.tt.internal.service.UserService;
import ee.teoreteetik.tt.internal.model.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("googleAuthService")
public class GoogleAuthService implements AuthService {

  @Resource private UserService userService;
  private static final String    TOKEN_INFO_URL = "https://www.googleapis.com/oauth2/v1/tokeninfo?access_token=";

  @Override
  public User getUser(String... params) {
    try {
      String googleAccessToken = params[0];
      URL url = new URL(TOKEN_INFO_URL + googleAccessToken);
      HttpURLConnection request = (HttpURLConnection) url.openConnection();
      request.setRequestMethod("GET");
      request.setDoOutput(true);
      request.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      OutputStreamWriter wr = new OutputStreamWriter(request.getOutputStream());
      wr.flush();
      request.connect();
      if(request.getResponseCode() != 200) {
        return null;
      }
      String responseBody = convertStreamToString(request.getInputStream());
      ObjectMapper mapper = new ObjectMapper();
      GoogleTokenResponse tokenResponse = mapper.readValue(responseBody, GoogleTokenResponse.class);
      wr.close();

      User user = userService.getByEmail(tokenResponse.getEmail());
      if (user == null) {
        user = new User();
        user.setEmail(tokenResponse.getEmail());
        user.setUsername("Nimetu");
        Set<Privilege> privileges = new HashSet<>();
        privileges.add(Privilege.ADD_TOPIC);
        privileges.add(Privilege.ADD_COMMENT);
        privileges.add(Privilege.ADD_SUBJECT);
        privileges.add(Privilege.DELETE_FOREIGN_TOPIC);
        privileges.add(Privilege.DELETE_FOREIGN_COMMENT);
        privileges.add(Privilege.DELETE_SUBJECT);
        user.setPrivileges(privileges);

        Long id = userService.create(user);

        user.setId(id);
      }
      return user;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private String convertStreamToString(InputStream in) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    StringBuilder sb = new StringBuilder();
    String line = null;
    try {
      while ((line = reader.readLine()) != null) {
        sb.append(line).append("\n");
      }
    } catch (IOException e) {
    } finally {
      try {
        in.close();
      } catch (IOException e) {
      }
    }
    return sb.toString();
  }
}
