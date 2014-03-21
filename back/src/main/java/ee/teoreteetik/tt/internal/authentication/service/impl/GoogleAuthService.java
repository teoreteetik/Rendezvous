package ee.teoreteetik.tt.internal.authentication.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.teoreteetik.tt.internal.authentication.GoogleTokenResponse;
import ee.teoreteetik.tt.internal.authentication.service.AuthService;
import ee.teoreteetik.tt.internal.service.UserService;
import ee.teoreteetik.tt.model.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("googleAuthService")
public class GoogleAuthService implements AuthService {

  @Autowired private UserService userService;
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
      String responseBody = convertStreamToString(request.getInputStream());
      ObjectMapper mapper = new ObjectMapper();
      GoogleTokenResponse tokenResponse = mapper.readValue(responseBody, GoogleTokenResponse.class);
      wr.close();

      User user = userService.getByEmail(tokenResponse.getEmail());
      if (user == null) {
        user = new User();
        user.setEmail(tokenResponse.getEmail());
        user.setUsername("Nimetu");
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
