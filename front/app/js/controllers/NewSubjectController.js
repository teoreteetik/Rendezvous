'use strict';
ngApp.controller('NewSubjectController', ['$scope', '$route', '$routeParams','$location', 'SubjectService',
  function NewSubjectController($scope, $route, $routeParams, $location, SubjectService){

    $scope.semesters = SubjectService.semesters;

    $scope.createSubject = function(subject){
      subject.year = $scope.selectedSemester.year;
      subject.semester = $scope.selectedSemester.semesterNumber;
      SubjectService.createSubject(subject).then(function(response){
        $location.path("/subjects/" + response + '/topics');
      });
    }
  }]
);