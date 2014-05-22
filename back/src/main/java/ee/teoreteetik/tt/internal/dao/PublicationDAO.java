package ee.teoreteetik.tt.internal.dao;

import ee.teoreteetik.tt.internal.model.Publication;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("publicationDAO")
public class PublicationDAO extends BaseDAO {

  public Long create(Publication publication) {
    String sql =
              "INSERT INTO publication "
            + "(text_plain, text_html, anonymous, user_id, date_posted) "
            + "VALUES (?, ?, ?, ?, ?) "
            + "RETURNING id";

    Long id = getJdbcTemplate().queryForLong(sql,
            publication.getTextPlain(),
            publication.getTextHtml(),
            publication.isAnonymous(),
            publication.getUserId(),
            publication.getDatePosted());
    return id;
  }

  public void markDeleted(Long publicationId) {
    String sql = "UPDATE publication SET sys_deleted = TRUE WHERE id = ?";
    getJdbcTemplate().update(sql, publicationId);
  }


}
