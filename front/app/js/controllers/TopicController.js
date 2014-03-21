'use strict';
ngApp.controller('TopicController',
  ['$scope', '$routeParams', 'TopicService',
    function ($scope, $routeParams, TopicService) {
      $scope.activeTopic = TopicService.getTopic($routeParams.topicId, false);
    }
  ]
);