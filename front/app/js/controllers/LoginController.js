'use strict';
ngApp.controller(
  'LoginController',
  ['$routeParams','$location', '$cookieStore', 'AuthService',
    function ($routeParams, $location, $cookieStore, AuthService) {
      AuthService.authenticate($routeParams.googleToken).then( //TODO POST
        function (response) {
          $cookieStore.put('rendezvous', response);
        }
      );
      if($routeParams.redirect != undefined){
        $location.path($routeParams.redirect);
      }else{
        $location.path('/');
      }
    }
  ]
);