'use strict';

ngApp.factory('SubjectService', function ($resource, Restangular) {

  //TODO semesters from back
  return {

    semesters: [
      {
        year: 2013,
        semesterNumber: 2,
        text: "2013/14 II"
      },
      {
        year: 2013,
        semesterNumber: 1,
        text: "2013/14 I"
      },
      {
        year: 2012,
        semesterNumber: 2,
        text: "2012/13 II"
      }
    ],

    getSubject: function(subjectId){
      if(subjectId != undefined){
        return Restangular.one('back/rest/subjects', subjectId).get().$object;
      }else{
        return {};
      }
    },

    getSemesterSubjects: function(year, semester){
      return Restangular.all('back/rest').customGETLIST("subjects", {year: year, semester: semester}).$object;
    },

    createSubject: function(subject){
      return Restangular.all('back/rest/subjects').post(subject);
    }
  }
});