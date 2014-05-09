package ee.teoreteetik.tt.internal.authentication.service;

import ee.teoreteetik.tt.internal.model.User;

public interface AuthService {
  User getUser(String... params);
}
