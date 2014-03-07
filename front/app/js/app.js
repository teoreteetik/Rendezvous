'use strict';

var ngApp = angular.module('ngApp', ['ngRoute', 'ngResource', 'restangular']);

ngApp.config(function($routeProvider){
  $routeProvider
    .when('/login', {  //Login page
      templateUrl:"templates/login.html",
      controller: "LoginController"
     })
    .when('/subjects/:subjectId/topics', { //View of topics by subject
      templateUrl:"templates/subjectTopics.html",
      controller: "SubjectController"
    })
    .when('/subjects/:subjectId/topics/:topicId', { //Single topic view
      templateUrl:"templates/viewTopic.html",
      controller: "TopicController"
    })
    .when('/subjects/createSubject', { //Creating a new subject
      templateUrl:"templates/createSubject.html",
      controller: "NewSubjectController"
    })
    .when('/subjects/:subjectId/createTopic', { //Creating a new topic
      templateUrl:"templates/createTopic.html",
      controller: "NewTopicController"
    });
});

ngApp.filter('toTrusted', ['$sce', function($sce){ //fixfixfix
  return function(text) {
    return $sce.trustAsHtml(text);
  };
}]);

