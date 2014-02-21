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


ngApp.factory('topicService', function () {
    var topics = [
        {
            title: "Kuidas see ülesanne käib?",
            id: 1,
            subjectId: 1,
            author: "Peeter Paan",
            datePublished: new Date('7/22/2008 12:11:04 PM'),
            text: "Maecenas justo dui, interdum sed sapien sed, facilisis accumsan dui. Nunc et tellus vel nisi interdum pretium. Ut nisi mi, facilisis eu dictum at, suscipit id enim. Nunc lobortis eget sapien sit amet porttitor. Vestibulum sed urna vel ipsum rutrum vulputate. Morbi quis lorem a nisl ullamcorper bibendum id eu metus. Duis sit amet nulla non libero bibendum bibendum eget ac quam. Duis a diam metus. Nulla condimentum tincidunt eros, sed semper ante pellentesque eu. In tempor mollis arcu vitae pellentesque. Etiam mollis viverra urna, nec congue quam sodales at. Fusce quis malesuada erat, eu ornare mi.",
            replies: [
                {
                    text: "Once upon a midnight dreary",
                    author: "anonüümus",
                    datePublished: "20.02.2014",
                    upvoteCount: 5
                },
                {
                    text: "While I pondered weak and weary",
                    author: "Mai Kelluke",
                    datePublished: "25.02.2014",
                    upvoteCount: 2
                },
                {
                    text: "My broken build, forevermore",
                    author: "Tim",
                    datePublished: "25.02.2014",
                    upvoteCount: 0
                },
                {
                    text: "Quoth the Maven - nevermore",
                    author: "ant",
                    datePublished: "25.02.2014",
                    upvoteCount: 1
                }
            ]
        },
        {
            title: "Keegi eilses mat. analüüsi loengus käis?",
            id: 2,
            subjectId: 1,
            author: "Anonüümus",
            datePublished: new Date('12/22/2013 10:11:04 PM'),
            text: "Maecenas justo dui, interdum sed sapien sed, facilisis accumsan dui. Nunc et tellus vel nisi interdum pretium. Ut nisi mi, facilisis eu dictum at, suscipit id enim. Nunc lobortis eget sapien sit amet porttitor. Vestibulum sed urna vel ipsum rutrum vulputate. Morbi quis lorem a nisl ullamcorper bibendum id eu metus. Duis sit amet nulla non libero bibendum bibendum eget ac quam. Duis a diam metus. Nulla condimentum tincidunt eros, sed semper ante pellentesque eu. In tempor mollis arcu vitae pellentesque. Etiam mollis viverra urna, nec congue quam sodales at. Fusce quis malesuada erat, eu ornare mi.",
            replies: [
                {
                    text: "Ma ei käinud",
                    author: "anonüümus",
                    datePublished: "20.02.2014",
                    upvoteCount: 0
                },
                {
                    text: "Räägiti integraalidest, muud midagi",
                    author: "Mai Kelluke",
                    datePublished: "25.02.2014",
                    upvoteCount: 2
                }
            ]
        },
        {
            title: "Eile KT lahendused",
            id: 3,
            subjectId: 2,
            author: "Helmut Sibul",
            datePublished: new Date('2/21/2014 9:20:04 PM'),
            text: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam vitae est tortor. Sed condimentum sodales eros eget malesuada. Donec viverra arcu sed vulputate varius. Nulla volutpat augue est, quis lacinia ligula tristique at. Nunc venenatis elementum risus, interdum laoreet eros consectetur non. Pellentesque tempus nisi vitae orci commodo aliquet. Sed tincidunt urna non turpis porttitor, a luctus neque suscipit. Donec vel nunc id nulla vulputate egestas ut vel massa.",
            replies: [
                {
                    text: "Tänud",
                    author: "anonüümus",
                    datePublished: "20.02.2014",
                    upvoteCount: 5
                },
                {
                    text: "Suddenly the build was jacking",
                    author: "Minemine",
                    datePublished: "25.02.2014",
                    upvoteCount: 5
                }
            ]
        }
    ];
    var getTopic =  function(id){
        for(var i=0; i<topics.length; i++){
            if(topics[i].id == id){
                return topics[i];
            }
        }
        return null;
    };

    return {

        getSubjectTopics: function(subjectId){
            var subjectTopics = [];
            for(var i=0; i<topics.length; i++) {
                if (topics[i].subjectId == subjectId){
                    subjectTopics.push(topics[i]);
                }
            }
            return subjectTopics;
        },

        getTopic: function(id){
            return getTopic(id);
        },

        newTopic: function(topic){
            topic.id = topics.length+1;
            topic.replies = [];
            topics.push(topic);
        },

        addReply: function(topicId, reply){
            var topic = getTopic(topicId);
            topic.replies.push(reply);
        }
    };

});




ngApp.config(function($routeProvider){
   //$locationProvider.html5Mode(true);
   $routeProvider
       .when('/subject=:subjectId', {
            templateUrl:"templates/subjectTopics.html",
            controller: "TopicCtrl"
       })
       .when('/login', {
            templateUrl:"templates/login.html",
            controller: "LoginCtrl"
       })
       .when('/subject=:subjectId/newTopic', {
            templateUrl:"templates/newTopic.html",
            controller: "NewTopicCtrl"
       })
       .when('/topic=:topicId', {
            templateUrl: "templates/viewTopic.html",
            controller: "TopicCtrl"
       });
});


ngApp.controller('LoginCtrl', function(){

});

ngApp.controller('NewTopicCtrl', ['$scope', '$routeParams', '$location', 'topicService', function($scope, $routeParams, $location, topicService){
    $scope.newTopic = function(topic){
        topic.subjectId = $routeParams.subjectId;
        topic.datePublished = new Date();
        topic.author = "Kasutajanimi";
        topicService.newTopic(topic);
        $location.path('/subject=' + $routeParams.subjectId);
    }

}]);


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




