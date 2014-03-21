'use strict';
ngApp.controller(
  'NewSubjectController',
  ['$scope', '$routeParams','$location', 'SubjectService',
    function ($scope, $routeParams, $location, SubjectService){

      SubjectService.getSemesters().then(function(response) {
        $scope.semesters = response;
        $scope.selectedSemester = $scope.semesters[0];
      });

      $scope.createSubject = function(subject) {
        subject.semesterId = $scope.selectedSemester.id;
        SubjectService.createSubject(subject).then(function (response) {
          $location.path("/subjects/" + response + '/topics');
        });
      }
    }
  ]
);