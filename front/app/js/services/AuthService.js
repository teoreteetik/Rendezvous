'use strict';
ngApp.factory(
  'AuthService',
  ['$cookieStore', 'Restangular', '$location',
    function ($cookieStore, Restangular, $location) {
      return {
        authenticate: function (googleToken) {
          return Restangular.one('back/rest/auth/google', googleToken).get();
        },
        logout: function () {
          var uuid = $cookieStore.get('rendezvous').uuid;
          Restangular.one('back/rest/auth/logout', uuid).get().then( //TODO POST
            function (response) {
              $cookieStore.remove('rendezvous');
              $location.path('/');
            }
          );
        },
        getAuthHeaders: function () {
          var token = $cookieStore.get('rendezvous');
          if (token != undefined) {
            return { 'Authorization': $cookieStore.get('rendezvous').uuid };
          } else {
            return {};
          }
        },
        isLoggedIn: function () {
          return $cookieStore.get('rendezvous') != undefined;
        },
        getCurrentUser: function () {
          if(this.isLoggedIn()){
            return $cookieStore.get('rendezvous').user;
          } else{
            return {};
          }
        },
        updateUser: function(user){
          var token = $cookieStore.get('rendezvous');
          token.user = user;
          $cookieStore.put('rendezvous', token);
        }
      };
    }
  ]
);