package ee.teoreteetik.tt.internal.service;

import ee.teoreteetik.tt.model.User;

public interface UserService {
  User getByEmail(String email);

  Long create(User user);

  void update(User user);
}
