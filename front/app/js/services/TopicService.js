'use strict';

ngApp.factory('TopicService', function ($resource, Restangular) {

  return {
    getTopic: function(topicId, forEdit){
      return Restangular.one('back/rest/topics', topicId).get({"forEdit": forEdit}).$object;
    },

    getSubjectTopics: function(subjectId){
      if(subjectId != undefined){
        return Restangular.one('back/rest/subjects', subjectId).all('topics').getList().$object;
      }else{
        return [];
      }
    },

    createTopic: function(topic){
      return Restangular.all('back/rest/topics').post(topic);
    }
  };

});