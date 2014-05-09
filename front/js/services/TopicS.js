'use strict';
ngApp.factory(
  'TopicS',
  ['Restangular',
    function (Restangular) {
      return {
        getTopic: function(topicId, forEdit) {
          return Restangular.one('topics', topicId).get({"forEdit": forEdit});
        },
        getSubjectTopics: function(subjectId) {
          if(subjectId != undefined) {
            return Restangular.one('subjects', subjectId).getList('topics').$object;
          } else {
            return [];
          }
        },
        addTopic: function(topic) {
          return Restangular.all('topics').post(topic);
        },
        addComment: function(comment) {
          return Restangular.all('topics/dummy/comments').post(comment);
        }
      };
    }
  ]
);