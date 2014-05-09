'use strict';
ngApp.controller(
  'MasterC',
  ['$scope', '$location', 'AuthS', 'SubjectS', 'SessionS', 'Restangular',
function ($scope, $location, AuthS, SubjectS, SessionS, Restangular) {
  $scope.subjectDropdown = {
    width:'300px'
  };
  $scope.semesterDropdown = {
    width: '150px'
  }
  $scope.auth = {
    login: function() {
      var state = encodeURI($location.path()).trim();
     // var loc = "https://accounts.google.com/o/oauth2/auth?state=" + state + "&scope=email%20profile&response_type=token&client_id=935110585409-8rpukqlertqd0t27p9i8f6k3jj3jntsd.apps.googleusercontent.com&redirect_uri=https://localhost:8443/front/auth";
      var loc = "https://accounts.google.com/o/oauth2/auth?state="+state+"&scope=email%20profile&response_type=token&client_id=935110585409.apps.googleusercontent.com&redirect_uri=https://ec2-54-213-178-112.us-west-2.compute.amazonaws.com/front/auth";
      window.location = loc;
    },
    isLoggedIn: function() {
      return AuthS.isLoggedIn();
    },
    hasPrivilege: function(privilegeCode) {
      return AuthS.hasPrivilege(privilegeCode);
    },
    getCurrentUser: function() {
      return AuthS.getCurrentUser();
    },
    logout: function () {
      AuthS.logout().then(function(result) {
        SessionS.unset('rendezvous');
        $location.path('/');
      });
    }
  };
  $scope.navState = {
    subjects: [],
    semesters: [],
    selectedSubjectId: undefined,
    selectedSemesterId: undefined
  }
  $scope.navStateHandler = {
    setSelectedSemesterId: function (semesterId) {
      $scope.navState.selectedSemesterId = semesterId;
      SubjectS.getSemesterSubjects(semesterId).then(function(result) {
        $scope.navState.subjects = result;
      });
    },
    setSelectedSubjectId: function(subjectId) {
      $scope.navState.selectedSubjectId = subjectId;
    },
    goToSubjectTopics: function() {
      if ($scope.navState.selectedSemesterId != '' && $scope.navState.selectedSubjectId != '') {
        $location.path('/semesters/' + $scope.navState.selectedSemesterId + '/subjects/' + $scope.navState.selectedSubjectId + '/topics');
      }
    },
    goToSubjectCloud: function() {
      if ($scope.navState.selectedSemesterId != '') {
        $location.path('/semesters/' + $scope.navState.selectedSemesterId + '/subjects');
      }
    }
  }
  SubjectS.getSemesters().then(function(result) {
    $scope.navState.semesters = result;
  });
  var token = SessionS.get('rendezvous');
  if(token != undefined) {
    AuthS.getToken(token.uuid).then(function(result){
      if(result != undefined) {
        Restangular.setDefaultHeaders({'Authorization': token.uuid});
      }else{
        SessionS.unset('rendezvous');
      }
    });
  }
}]
);