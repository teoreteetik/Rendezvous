'use strict';
ngApp.controller('NewTopicController', ['$scope', '$route', '$routeParams','$location', 'SubjectService', 'TopicService',
  function NewTopicController($scope, $route, $routeParams, $location, SubjectService, TopicService){

    $scope.createTopic = function(topic){
      console.log(topic);
      topic.subjectId = $routeParams.subjectId;
      topic.userId = 1;
      topic.datePosted = new Date();
      topic.textHtml = $('#marked-mathjax-preview').html();
      TopicService.createTopic(topic).then(function(response){
        $location.path("/subjects/" + topic.subjectId + '/topics/' + response);
      });
    }

  }]
);