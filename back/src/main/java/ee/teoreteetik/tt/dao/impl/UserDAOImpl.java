package ee.teoreteetik.tt.dao.impl;

import ee.teoreteetik.tt.dao.UserDAO;
import ee.teoreteetik.tt.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("userDAO")
public class UserDAOImpl extends JdbcDaoSupport implements UserDAO {

  @Autowired
  public UserDAOImpl(DataSource ds) {
    setDataSource(ds);
  }

  @Override
  public User loadById(Long id) {
    String sql = "SELECT * FROM USERS WHERE ID = ?";
    User user = getJdbcTemplate().query(sql, new Object[] { id },
        new ParameterizedRowMapper<User>() {
          @Override
          public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setName(rs.getString("USERNAME"));
            return user;
          }
        }).get(0);
    return user;
  }

}
