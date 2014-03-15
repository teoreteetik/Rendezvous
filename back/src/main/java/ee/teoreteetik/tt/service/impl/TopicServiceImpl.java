package ee.teoreteetik.tt.service.impl;

import ee.teoreteetik.tt.dao.impl.TopicDAO;
import ee.teoreteetik.tt.model.Topic;
import ee.teoreteetik.tt.service.TopicService;
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
