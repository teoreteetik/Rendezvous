'use strict';
ngApp.controller('SubjectCtrl', ['$scope', '$route', '$routeParams', 'subjectService',
    function SubjectCtrl($scope, $route, $routeParams, subjectService){

        $scope.activeSubject = subjectService.getSubject($routeParams.subjectId);

        $scope.subjects = subjectService.getSubjects();
    }]
);