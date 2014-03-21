'use strict';
ngApp.controller(
  'AuthController',
  ['$scope', '$route', '$routeParams','$location', 'AuthService',
    function ($scope, $route, $routeParams, $location, AuthService) {

      $scope.login = function(){
        var state = encodeURI($location.path()).trim();
        var loc = "https://accounts.google.com/o/oauth2/auth?state="+encodeURIComponent($location.path())+"&scope=email%20profile&response_type=token&client_id=935110585409.apps.googleusercontent.com&redirect_uri=https://ec2-54-213-178-112.us-west-2.compute.amazonaws.com/front/auth";
        window.location = loc;
      };

      $scope.isLoggedIn = function(){
        return AuthService.isLoggedIn();
      };

      $scope.currentUser = AuthService.getCurrentUser();

      $scope.logout = function (){
        AuthService.logout();
      }
    }
  ]
);