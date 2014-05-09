package ee.teoreteetik.tt.internal.service;

import ee.teoreteetik.tt.internal.model.User;

public interface UserService {
  User getByEmail(String email);

  Long create(User user);

  void update(User user);

}
