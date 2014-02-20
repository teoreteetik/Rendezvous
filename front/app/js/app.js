'use strict';

var ngApp = angular.module('ngApp', ['ngRoute']);

ngApp.factory('subjectService', function () {
    var subjects= [
        {
            code: "MTAT.03.134",
            name: "Andmeturve",
            id: 1
        },
        {
            code: "MTAT.03.032",
            name: "Kasutajaliideste kavandamine",
            id: 2
        },
        {
            code: "MTAT.06.045",
            name: "Keeletehnoloogia",
            id: 3
        },
        {
            code: "MTAT.03.159",
            name: "Tarkvara testimine",
            id: 4
        },
        {
            code: "MTMS.02.049",
            name: "Tõenäosusteooria ja statistika",
            id: 5
        },
        {
            code: "MTAT.03.230",
            name: "Veebirakenduste loomine",
            id: 6
        }
    ];
    return {


    getSubject: function(subjectId){
        for(var i=0; i<subjects.length; i++) {
            if (subjects[i].id == subjectId) return subjects[i];
        }
        return null;
    },

    getSubjects: function(){
        return subjects;
    }
    }
});

ngApp.config(function($routeProvider){
   $routeProvider
   .when('/newTopic', {
      templateUrl: "templates/newTopic.html",
      controller: "NewTopicCtrl"})
   .when('/subject/:subjectId', {
        templateUrl:"templates/subjectTopics.html",
        controller: "TopicCtrl"
   })
   .when('/login', {
        templateUrl:"templates/login.html",
        controller: "LoginCtrl"
   })
   .when('/newTopic/:subjectId', {
        templateUrl:"templates/newTopic.html",
        controller: "NewTopicCtrl"
   })
   .when('/topic/:topicId', {
        templateUrl: "templates/viewTopic.html",
        controller: "TopicCtrl"
   });
});

ngApp.controller('NewTopicCtrl', function(){

});

ngApp.controller('LoginCtrl', function(){

});

ngApp.controller('NewTopicCtrl', function(){

});


ngApp.filter('searchFor', function(){

    return function(arr, searchString){
        if(!searchString){
            return arr;
        }
        var result = [];
        searchString = searchString.toLowerCase();
        angular.forEach(arr, function(item){
            if(item.name.toLowerCase().indexOf(searchString) !== -1 || item.code.toLowerCase().indexOf(searchString) !== -1 ){
                result.push(item);
            }
        });
        return result;
    };
});




