'use strict';
ngApp.factory(
  'UserService',
  ['Restangular', 'AuthService',
    function (Restangular, AuthService) {
      return {
        update: function (user) {
          return Restangular.all('back/rest/users').post(user, {}, AuthService.getAuthHeaders());
        }
      };
    }
  ]
);