package ee.teoreteetik.tt.internal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;

public class BaseDAO extends JdbcDaoSupport {

  @Autowired
  protected void setDs(DataSource ds) {
    super.setDataSource(ds);
  }

}
