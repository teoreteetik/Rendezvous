package ee.teoreteetik.tt.internal.dao;

import ee.teoreteetik.tt.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("userDAO")
public class UserDAO extends JdbcDaoSupport {

  @Autowired
  public UserDAO(DataSource ds) {
    setDataSource(ds);
  }

  public User loadByEmail(String email) {
    String sql = "SELECT * FROM users u WHERE u.email = ?";
    List<User> users = getJdbcTemplate().query(sql, new Object[] { email },
        new ParameterizedRowMapper<User>() {
          @Override
          public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setEmail(rs.getString("email"));
            user.setUsername(rs.getString("username"));
            return user;
          }
        });
    if (users.size() != 1) {
      return null;
    }
    return users.get(0);
  }

  public Long create(User user) {
    String sql = "INSERT INTO users (username, email) VALUES (?, ?) RETURNING id";
    Long id = getJdbcTemplate().queryForLong(sql, new Object[] {
        user.getUsername(),
        user.getEmail()
    });
    return id;
  }

  public void update(User user) {
    String sql = "UPDATE users SET username = ? WHERE id = ?";
    getJdbcTemplate().update(sql, new Object[] {
        user.getUsername(),
        user.getId()
    });
  }

}
