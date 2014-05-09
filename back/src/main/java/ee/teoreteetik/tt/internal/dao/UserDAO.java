package ee.teoreteetik.tt.internal.dao;

import ee.teoreteetik.tt.internal.authentication.Privilege;
import ee.teoreteetik.tt.internal.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("userDAO")
public class UserDAO extends BaseDAO {



  public User loadByEmail(String email) {
    String sql = "SELECT u.id, u.email, u.username, STRING_AGG(code, ',') AS privileges "
        + "FROM users u "
        + "LEFT JOIN user_privilege up ON u.id = up.user_id "
        + "LEFT JOIN privilege p ON up.privilege_id = p.id "
        + "WHERE u.email = ? "
        + "GROUP BY u.id, u.email, u.username";
    List<User> users = getJdbcTemplate().query(sql, new Object[] { email },
        new ParameterizedRowMapper<User>() {
          @Override
          public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setEmail(rs.getString("email"));
            user.setUsername(rs.getString("username"));
            Set<Privilege> privileges = new HashSet<>();
            String privilegeCodes = rs.getString("privileges");
            if (privilegeCodes != null) {
              for (String privilegeCode : privilegeCodes.split(",")) {
                privileges.add(Privilege.valueOf(privilegeCode));
              }
            }
            user.setPrivileges(privileges);
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

    //TODO ugly
    for(Privilege privilege : user.getPrivileges()) {
      sql = "INSERT INTO user_privilege (user_id, privilege_id) (SELECT ?, p.id FROM privilege p WHERE p.code = ?)";
      getJdbcTemplate().update(sql, id, privilege.name());
    }

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
