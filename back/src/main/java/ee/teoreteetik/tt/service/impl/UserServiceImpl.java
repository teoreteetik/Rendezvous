package ee.teoreteetik.tt.service.impl;

import ee.teoreteetik.tt.dao.impl.UserDAO;
import ee.teoreteetik.tt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

  @Autowired UserDAO userDAO;

}
