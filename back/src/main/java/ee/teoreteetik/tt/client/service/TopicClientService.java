package ee.teoreteetik.tt.client.service;

import ee.teoreteetik.tt.client.model.TopicRepresentation.CreateTopic;
import ee.teoreteetik.tt.client.model.TopicRepresentation.TopicForListDisplay;
import ee.teoreteetik.tt.client.model.TopicRepresentation.TopicForDisplay;
import ee.teoreteetik.tt.internal.model.User;

import java.util.List;

public interface TopicClientService {
  TopicForDisplay getTopicForDisplay(Long topicId);
  List<TopicForListDisplay> getTopicsBySubjectId(Long subjectId);
  Long createTopic(CreateTopic topicToCreate, User user);
  void deleteTopic(Long topicId, User user);
}
