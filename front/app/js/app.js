'use strict';
var ngApp = angular.module('ngApp', ['ngRoute', 'ngResource', 'restangular', 'ngCookies']);
ngApp.config(function ($routeProvider) {
  $routeProvider
    .when('/login/:googleToken/:redirect*',{
      controller:"LoginController",
      template: ""
    })
    .when('/login/:googleToken',{
      controller:"LoginController",
      template: ""
    })
    .when('/subjects/:subjectId/topics', {
      templateUrl:"templates/subjectTopics.html",
      controller: "SubjectController"
    })
    .when('/subjects/:subjectId/topics/:topicId', {
      templateUrl:"templates/viewTopic.html",
      controller: "TopicController"
    })
    .when('/subjects/createSubject', {
      templateUrl:"templates/createSubject.html",
      controller: "NewSubjectController"
    })
    .when('/subjects/:subjectId/createTopic', {
      templateUrl:"templates/createTopic.html",
      controller: "NewTopicController"
    })
    .when('/config', {
      templateUrl:"templates/config.html",
      controller: "ConfigController"
    })
});

ngApp.filter('toTrusted', ['$sce', function($sce) { //fixfixfix
  return function(text) {
    return $sce.trustAsHtml(text);
  };
}]);

