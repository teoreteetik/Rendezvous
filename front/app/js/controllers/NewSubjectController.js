'use strict';
ngApp.controller('NewSubjectController', ['$scope', '$route', '$routeParams','$location', 'SubjectService',
  function NewSubjectController($scope, $route, $routeParams, $location, SubjectService){

    SubjectService.getSemesters().then(function(response){
      $scope.semesters = response;
      $scope.selectedSemester = $scope.semesters[0];
    });

    $scope.createSubject = function(subject){
      subject.semesterId = $scope.selectedSemester.id;
      SubjectService.createSubject(subject).then(function(response){
        $location.path("/subjects/" + response + '/topics');
      });
    }
  }]
);