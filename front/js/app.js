'use strict';
var ngApp = angular.module('ngApp', ['ui.router', 'ui.select2', 'ngResource', 'restangular', 'ngCookies']);
ngApp.config(['RestangularProvider', '$stateProvider', function (RestangularProvider, $stateProvider) {
  $stateProvider
    .state('master', {
      url: '/',
      templateUrl: 'templates/master.html',
      controller: 'MasterC'
    })
    .state('master.semester', {
      url: 'semesters/:semesterId',
      template: '<div data-ui-view/>',
      controller: ['$scope', '$stateParams', function($scope, $stateParams) {
        $scope.$parent.navStateHandler.setSelectedSemesterId($stateParams.semesterId);
      }]
    })
    .state('master.semester.subjects', {
      url: '/subjects',
      template: 'See pilv tuleb ilusamaks teha<div data-word-cloud data-words="subjects" data-type="cloud"><a href="#/semesters/{{word.obj.semesterId}}/subjects/{{word.obj.id}}/topics">{{word.word}}</a></div>',
      controller: ['$scope', '$stateParams', 'SubjectS', function($scope, $stateParams, SubjectS) {
        $scope.subjects = [];
        $scope.semesterId = $stateParams.semesterId;
        SubjectS.getSemesterSubjects($stateParams.semesterId).then(function(result) {
          for (var i = 0 ; i < result.length ; i++) {
            var s = result[i];
            $scope.subjects.push({ word: s.name + ' (' + s.code + ')',
                                   size: s.topicCount,
                                   obj: s });
          }
        });
      }]
    })
    .state('master.semester.topics', {
      url: '/subjects/:subjectId/topics',
      templateUrl: 'templates/subjectTopics.html',
      controller: ['$scope', '$stateParams', 'SubjectS', function($scope, $stateParams, SubjectS) {
        $scope.$parent.navStateHandler.setSelectedSubjectId($stateParams.subjectId);
        SubjectS.getSubjectTopics($stateParams.subjectId).then(function(result) {
          $scope.topics = result;
        });
      }]
    })
    .state('master.semester.topic', {
      url: '/subjects/:subjectId/topics/:topicId',
      templateUrl: 'templates/viewTopic.html',
      controller: ['$scope', '$stateParams', 'TopicS', function($scope, $stateParams, TopicS) {
        $scope.$parent.navStateHandler.setSelectedSubjectId($stateParams.subjectId);
        TopicS.getTopic($stateParams.topicId, false).then(function(result) {
          $scope.topic = result;
        });
        $scope.addComment = function(comment) {
          comment.parentPublicationId = $stateParams.topicId;
          comment.textHtml = $('#marked-mathjax-preview').html();
          TopicS.addComment(comment).then(function(result) {
            $scope.topic.comments.push(comment);
          });
        }
      }]
    })
    .state('master.semester.addTopic', {
      url: '/subjects/:subjectId/addTopic',
      templateUrl: 'templates/addTopic.html',
      controller: ['$scope', '$stateParams', '$location', 'TopicS', function($scope, $stateParams, $location, TopicS) {
        $scope.$parent.navStateHandler.setSelectedSubjectId($stateParams.subjectId);
        $scope.addTopic = function(topic) {
          topic.subjectId = $stateParams.subjectId;
          topic.textHtml = $('#marked-mathjax-preview').html();
          TopicS.addTopic(topic).then(function (response) {
            $location.path('/semesters/' + $stateParams.semesterId + '/subjects/' + topic.subjectId + '/topics/' + response);
          });
        }
      }]
    })
    .state('master.addSubject', {
      url: 'addSubject',
      templateUrl: 'templates/addSubject.html',
      controller: ['$scope', 'SubjectS', '$location', function($scope, SubjectS, $location) {
        $scope.addSubject = function(subject) {
          subject.semesterId = $scope.selectedSemester.id;
          SubjectS.addSubject(subject).then(function (response) {
            $location.path('/semesters/' + subject.semesterId + '/subjects/' + response + '/topics');
          });
        }
      }]
    })
    .state('login', {
      url: '/login?token&redirect',
      controller: ['$stateParams', '$location', 'AuthS', 'SessionS', function($stateParams, $location, AuthS, SessionS) {
        AuthS.login($stateParams.token).then(function(result) {
          SessionS.set('rendezvous', result);
          RestangularProvider.setDefaultHeaders({'Authorization': result.uuid});
          $location.url($stateParams.redirect);
        }, function() {
          $location.url('/');
        });
      }]
    })
    .state('master.config', {
      url: 'config',
      templateUrl: 'templates/config.html',
      controller: ['$scope', '$stateParams', 'AuthS', function ($scope, $stateParams, AuthS) {
        $scope.user = AuthS.getCurrentUser();
        $scope.update = function() {
          AuthS.updateUserInfo($scope.user).then(function (response) {
            AuthS.updateUser(response);
            $scope.message = "OK";
          });
        };
      }]
    });
    RestangularProvider.setBaseUrl('/back/rest');
  }
]);

ngApp.factory('SessionS', ['$cookieStore', function($cookieStore) {
  return {
    get:    function(key) {         return $cookieStore.get(key); },
    set:    function(key, value) {  $cookieStore.put(key, value); },
    unset:  function(key) {         $cookieStore.remove(key); }
  }
}]);

ngApp.filter('toTrusted', ['$sce', function($sce) { //fixfixfix
  return function(text) {
    return $sce.trustAsHtml(text);
  };
}]);