'use strict';
ngApp.controller('TopicCtrl', ['$scope', '$route', '$routeParams', 'subjectService',
    function TopicCtrl($scope, $route, $routeParams, subjectService){
        $scope.topics = [
            {
                title: "Kuidas see ülesanne käib?",
                topicId: 1,
                subjectId: 1,
                author: "Peeter Paan",
                datePublished: "16.02.2014",
                text: "Maecenas justo dui, interdum sed sapien sed, facilisis accumsan dui. Nunc et tellus vel nisi interdum pretium. Ut nisi mi, facilisis eu dictum at, suscipit id enim. Nunc lobortis eget sapien sit amet porttitor. Vestibulum sed urna vel ipsum rutrum vulputate. Morbi quis lorem a nisl ullamcorper bibendum id eu metus. Duis sit amet nulla non libero bibendum bibendum eget ac quam. Duis a diam metus. Nulla condimentum tincidunt eros, sed semper ante pellentesque eu. In tempor mollis arcu vitae pellentesque. Etiam mollis viverra urna, nec congue quam sodales at. Fusce quis malesuada erat, eu ornare mi.",
                replies: [
                    {
                        text: "Once upon a midnight dreary",
                        author: "anonüümus",
                        datePublished: "20.02.2014"
                    },
                    {
                        text: "While I pondered weak and weary",
                        author: "Mai Kelluke",
                        datePublished: "25.02.2014"
                    },
                    {
                        text: "My broken build, forevermore",
                        author: "Tim",
                        datePublished: "25.02.2014"
                    },
                    {
                        text: "Quoth the Maven - nevermore",
                        author: "ant",
                        datePublished: "25.02.2014"
                    }
                ]
            },
            {
                title: "Keegi eilses mat. analüüsi loengus käis?",
                topicId: 2,
                subjectId: 1,
                author: "Anonüümus",
                datePublished: "16.02.2014",
                text: "Maecenas justo dui, interdum sed sapien sed, facilisis accumsan dui. Nunc et tellus vel nisi interdum pretium. Ut nisi mi, facilisis eu dictum at, suscipit id enim. Nunc lobortis eget sapien sit amet porttitor. Vestibulum sed urna vel ipsum rutrum vulputate. Morbi quis lorem a nisl ullamcorper bibendum id eu metus. Duis sit amet nulla non libero bibendum bibendum eget ac quam. Duis a diam metus. Nulla condimentum tincidunt eros, sed semper ante pellentesque eu. In tempor mollis arcu vitae pellentesque. Etiam mollis viverra urna, nec congue quam sodales at. Fusce quis malesuada erat, eu ornare mi.",
                replies: [
                    {
                        text: "Ma ei käinud",
                        author: "anonüümus",
                        datePublished: "20.02.2014"
                    },
                    {
                        text: "Räägiti integraalidest, muud midagi",
                        author: "Mai Kelluke",
                        datePublished: "25.02.2014"
                    }
                ]
            },
            {
                title: "Eile KT lahendused",
                topicId: 3,
                subjectId: 2,
                author: "Helmut Sibul",
                datePublished: "16.02.2014",
                text: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam vitae est tortor. Sed condimentum sodales eros eget malesuada. Donec viverra arcu sed vulputate varius. Nulla volutpat augue est, quis lacinia ligula tristique at. Nunc venenatis elementum risus, interdum laoreet eros consectetur non. Pellentesque tempus nisi vitae orci commodo aliquet. Sed tincidunt urna non turpis porttitor, a luctus neque suscipit. Donec vel nunc id nulla vulputate egestas ut vel massa.",
                replies: [
                    {
                        text: "Tänud",
                        author: "anonüümus",
                        datePublished: "20.02.2014"
                    },
                    {
                        text: "Suddenly the build was jacking",
                        author: "Minemine",
                        datePublished: "25.02.2014"
                    }
                ]
            }
        ];



        var getSubjectTopics = function(subjectId){
            var subjectTopics = [];
            for(var i=0; i<$scope.topics.length; i++) {
                if ($scope.topics[i].subjectId == subjectId){
                    subjectTopics.push($scope.topics[i]);
                }
            }
            return subjectTopics;
        };

        var getTopic = function(topicId){
            for(var i=0; i<$scope.topics.length; i++){
                if($scope.topics[i].topicId == topicId){
                    console.log($scope.topics[i].id + "  " + topicId);
                    return $scope.topics[i];
                }
            }
            return null;
        };

        $scope.activeTopics = getSubjectTopics($routeParams.subjectId);

        $scope.activeTopic = getTopic($routeParams.topicId);

        $scope.activeSubject = subjectService.getSubject($routeParams.subjectId);

    }]
);