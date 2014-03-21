'use strict';
ngApp.controller(
  'SubjectController',
  ['$scope', '$routeParams','$location', 'SubjectService', 'TopicService',
    function ($scope, $routeParams, $location, SubjectService, TopicService) {
      $scope.updateSubjectView = function (subject) {
        if (subject != undefined) {
          $location.path("/subjects/" + subject.id + '/topics');
        }
      };

      $scope.updateSubjectDropdown = function (semester) {
        $scope.semesterSubjects = SubjectService.getSemesterSubjects(semester.year, semester.semesterNumber);
      };

      SubjectService.getSemesters().then(function (response) {
        $scope.semesters = response;
        $scope.selectedSemester = $scope.semesters[0];
        $scope.updateSubjectDropdown($scope.selectedSemester);
      });

      $scope.activeSubject = SubjectService.getSubject($routeParams.subjectId);
      $scope.activeTopics = TopicService.getSubjectTopics($routeParams.subjectId);
    }
  ]
);