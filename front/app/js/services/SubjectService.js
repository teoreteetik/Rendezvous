'use strict';
ngApp.factory(
  'SubjectService',
  ['Restangular', 'AuthService',
    function (Restangular, AuthService) {
      return {
        getSemesters: function() {
          return Restangular.one('back/rest').getList('semesters');
        },
        getSubject: function(subjectId) {
          if(subjectId != undefined) {
            return Restangular.one('back/rest/subjects', subjectId).get().$object;
          } else {
            return {};
          }
        },
        getSemesterSubjects: function(year, semester){
          return Restangular.all('back/rest').customGETLIST('subjects', { year: year, semesterNumber: semester }).$object;
        },
        createSubject: function(subject){
          return Restangular.all('back/rest/subjects').post(subject, {}, AuthService.getAuthHeaders());
        }
      }
    }
  ]
);