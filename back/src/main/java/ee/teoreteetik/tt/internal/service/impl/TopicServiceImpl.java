package ee.teoreteetik.tt.internal.service.impl;

import ee.teoreteetik.tt.internal.dao.TopicDAO;
import ee.teoreteetik.tt.internal.service.TopicService;
import ee.teoreteetik.tt.model.Topic;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("topicService")
public class TopicServiceImpl implements TopicService {

  @Autowired private TopicDAO topicDAO;

  @Override
  public List<Topic> getBySubjectId(Long subjectId) {
    return topicDAO.loadSubjectTopics(subjectId);
  }

  @Override
  public Topic getById(Long topicId, boolean forEdit) {
    Topic topic = topicDAO.loadById(topicId);
    if (topic == null) {
      return topic;
    }
    if (forEdit) {
      topic.setTextHtml(null);
    } else {
      topic.setTextPlain(null);
    }
    return topic;
  }

  @Override
  public Long createTopic(Topic topic) {
    return topicDAO.create(topic);
  }
}
