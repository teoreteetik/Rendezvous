'use strict';

ngApp.factory('SubjectService', function ($resource, Restangular) {

  return {

    getSemesters: function() {
      return Restangular.one('back/rest').getList('semesters');
    },


    getSubject: function(subjectId){
      if(subjectId != undefined){
        return Restangular.one('back/rest/subjects', subjectId).get().$object;
      }else{
        return {};
      }
    },

    getSemesterSubjects: function(year, semester){
      return Restangular.all('back/rest').customGETLIST('subjects', {year: year, semester: semester}).$object;
    },

    createSubject: function(subject){
      return Restangular.all('back/rest/subjects').post(subject);
    }
  }
});