package ee.teoreteetik.tt.internal.service.impl;

import ee.teoreteetik.tt.internal.dao.CommentDAO;
import ee.teoreteetik.tt.internal.dao.PublicationDAO;
import ee.teoreteetik.tt.internal.model.Comment;
import ee.teoreteetik.tt.internal.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

  @Autowired
  private PublicationDAO publicationDAO;
  @Autowired
  private CommentDAO commentDAO;


  @Override
  public Long createComment(Comment comment) {
    Validate.isTrue(!StringUtils.isBlank(comment.getTextPlain()));
    Validate.isTrue(!StringUtils.isBlank(comment.getTextHtml()));

    Long id = publicationDAO.create(comment);
    comment.setId(id);
    return commentDAO.create(comment);

  }



}
