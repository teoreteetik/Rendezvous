'use strict';
ngApp.controller('SubjectController', ['$scope', '$route', '$routeParams','$location', 'SubjectService', 'TopicService',
  function SubjectController($scope, $route, $routeParams, $location, SubjectService, TopicService){

    $scope.updateSubjectView = function(subject){
      if(subject != undefined){
        $location.path("/subjects/" + subject.id + '/topics');
      }
    }

    $scope.updateSubjectDropdown = function(semester){
      $scope.semesterSubjects = SubjectService.getSemesterSubjects(semester.year, semester.semesterNumber);
    }

    $scope.semesters = SubjectService.semesters;
    $scope.selectedSemester = $scope.semesters[0];
    $scope.updateSubjectDropdown($scope.semesters[0]);

    $scope.activeSubject = SubjectService.getSubject($routeParams.subjectId);
    $scope.activeTopics = TopicService.getSubjectTopics($routeParams.subjectId);

  }]
);