package ee.teoreteetik.tt.client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;

public class BaseClientServiceImpl extends JdbcDaoSupport {

  @Autowired
  protected void setDs(DataSource ds) {
    super.setDataSource(ds);
  }
}
