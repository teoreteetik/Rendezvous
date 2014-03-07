'use strict';
ngApp.controller('TopicController', ['$scope', '$route', '$routeParams', 'SubjectService', 'TopicService',
    function TopicController($scope, $route, $routeParams, SubjectService, TopicService){

      $scope.activeTopic = TopicService.getTopic($routeParams.topicId, false);

    }]
);