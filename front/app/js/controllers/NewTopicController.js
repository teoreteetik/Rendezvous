'use strict';
ngApp.controller(
  'NewTopicController',
  ['$scope', '$routeParams', '$location', 'TopicService', 'AuthService',
    function ($scope, $routeParams, $location, TopicService, AuthService) {

      $scope.createTopic = function(topic) {
        topic.subjectId = $routeParams.subjectId;
        topic.datePosted = new Date();
        topic.textHtml = $('#marked-mathjax-preview').html();
        TopicService.createTopic(topic).then(function (response) {
          $location.path("/subjects/" + topic.subjectId + '/topics/' + response);
        });
      }

    }
  ]
);