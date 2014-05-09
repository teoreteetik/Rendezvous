package ee.teoreteetik.tt.internal.service.impl;

import ee.teoreteetik.tt.internal.dao.PublicationDAO;
import ee.teoreteetik.tt.internal.dao.TopicDAO;
import ee.teoreteetik.tt.internal.service.TopicService;
import ee.teoreteetik.tt.internal.model.Topic;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("topicService")
public class TopicServiceImpl implements TopicService {

  @Resource private TopicDAO topicDAO;
  @Resource private PublicationDAO publicationDAO;

  @Override
  public Long createTopic(Topic topic) {
    Validate.isTrue(!StringUtils.isBlank(topic.getTitle()));
    Validate.isTrue(!StringUtils.isBlank(topic.getTextHtml()));
    Validate.isTrue(!StringUtils.isBlank(topic.getTextPlain()));

    Long id = publicationDAO.create(topic);
    topic.setId(id);
    return topicDAO.create(topic);
  }
}
