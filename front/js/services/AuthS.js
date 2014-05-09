'use strict';
ngApp.factory(
  'AuthS',
  ['Restangular', 'SessionS',
    function (Restangular, SessionS) {
      return {
        login: function (token) {
          return Restangular.one('auth/google', token).get();
        },
        isLoggedIn: function () {
          return SessionS.get('rendezvous') != undefined;
        },
        logout: function () {
          var uuid = SessionS.get('rendezvous').uuid;
          return Restangular.one('auth/logout', uuid).get();
        },
        hasPrivilege: function(privilegeCode) {
          var privileges = this.getCurrentUser().privileges;
          if (privileges != undefined) {
            return privileges.indexOf(privilegeCode) != -1;
          }
          return false;
        },
        getCurrentUser: function () {
          if(this.isLoggedIn()){
            return SessionS.get('rendezvous').user;
          } else{
            return {};
          }
        },
        updateUser: function(user){
          var token = SessionS.get('rendezvous');
          token.user = user;
          SessionS.set('rendezvous', token);
        },
        getToken: function(uuid) {
          return Restangular.one('auth/google/getUser', uuid).get();
        },
        updateUserInfo: function (user) {
          return Restangular.all('users').post(user);
        }
      };
    }
  ]
);