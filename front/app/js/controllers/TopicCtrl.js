'use strict';
ngApp.controller('TopicCtrl', ['$scope', '$route', '$routeParams', 'subjectService', 'topicService',
    function TopicCtrl($scope, $route, $routeParams, subjectService, topicService){

        $scope.activeTopics = topicService.getSubjectTopics($routeParams.subjectId);

        $scope.activeTopic = topicService.getTopic($routeParams.topicId);

        $scope.activeSubject = subjectService.getSubject($routeParams.subjectId);

        $scope.addReply = function(reply){
            reply.datePublished = new Date();
            reply.author = "komentaator";
            reply.upvoteCount = 0;
            topicService.addReply($routeParams.topicId, reply);
            $scope.replyForm.$setPristine();
            $scope.reply = {}
        }

        $scope.upvote = function(reply){
            reply.upvoteCount++;
        }

    }]
);