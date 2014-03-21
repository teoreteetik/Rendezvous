'use strict';
ngApp.factory(
  'TopicService',
  ['Restangular', 'AuthService',
    function (Restangular, AuthService) {
      return {
        getTopic: function(topicId, forEdit) {
          return Restangular.one('back/rest/topics', topicId).get({"forEdit": forEdit}).$object;
        },
        getSubjectTopics: function(subjectId) {
          if(subjectId != undefined) {
            return Restangular.one('back/rest/subjects/', subjectId).getList('topics').$object;
          } else {
            return [];
          }
        },
        createTopic: function(topic) {
          return Restangular.all('back/rest/topics').post(topic, {}, AuthService.getAuthHeaders());
        }
      };
    }
  ]
);