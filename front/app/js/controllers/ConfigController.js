'use strict';
ngApp.controller(
  'ConfigController',
  ['$scope', '$routeParams', 'AuthService', 'UserService',
    function ($scope, $routeParams, AuthService, UserService) {

      $scope.user = AuthService.getCurrentUser();

      $scope.update = function() {
        UserService.update($scope.user).then(function (response) {
          AuthService.updateUser(response);
          $scope.message = "OK";
        });
      };
    }
  ]
);