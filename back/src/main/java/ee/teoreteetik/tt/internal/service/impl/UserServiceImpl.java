package ee.teoreteetik.tt.internal.service.impl;

import ee.teoreteetik.tt.internal.dao.UserDAO;
import ee.teoreteetik.tt.internal.service.UserService;
import ee.teoreteetik.tt.model.User;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

  @Autowired UserDAO userDAO;

  @Override
  public User getByEmail(String email) {
    return userDAO.loadByEmail(email);
  }

  @Override
  public Long create(User user) {
    return userDAO.create(user);
  }

  @Override
  public void update(User user) {
    Validate.notBlank(user.getUsername());
    userDAO.update(user);
  }

}
