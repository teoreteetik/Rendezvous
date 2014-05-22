package ee.teoreteetik.tt.client.service;

import ee.teoreteetik.tt.client.model.CommentRepresentation;
import ee.teoreteetik.tt.client.model.CommentRepresentation.CommentForDisplay;
import ee.teoreteetik.tt.internal.model.User;

import java.util.List;

public interface CommentClientService {

  List<CommentForDisplay> getCommentsForTopic(Long topicId);

  Long createComment(CommentRepresentation.CreateComment commentToCreate, User user);

  void deleteComment(Long commentId, User user);
}
