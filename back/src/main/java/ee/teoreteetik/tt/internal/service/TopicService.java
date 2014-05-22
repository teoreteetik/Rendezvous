package ee.teoreteetik.tt.internal.service;

import ee.teoreteetik.tt.internal.model.Topic;

public interface TopicService {
  Long createTopic(Topic topic);
  void deleteTopic(Long topicId);
}
