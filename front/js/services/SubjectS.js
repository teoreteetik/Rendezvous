'use strict';
ngApp.factory(
  'SubjectS',
  ['Restangular',
    function (Restangular) {
      return {
        getSemesters: function() {
            return Restangular.all('semesters').getList();
          },
        addSubject: function(subject) {
          return Restangular.all('subjects').post(subject);
        },
        getSubjectTopics: function(subjectId) {
            return Restangular.one('subjects', subjectId).getList('topics');
        },
        getSemesterSubjects: function(semesterId) {
            return Restangular.one('semesters', semesterId).getList('subjects');
        },
        createSubject: function(subject){
          return Restangular.all('subjects').post(subject);
        }
      }
    }
  ]
);