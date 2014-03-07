package ee.teoreteetik.tt.service;

import ee.teoreteetik.tt.model.Topic;
import java.util.List;

public interface TopicService {
  List<Topic> getBySubjectId(Long subjectId);

  Topic getById(Long topicId, boolean forEdit);

  Long createTopic(Topic topic);

}
