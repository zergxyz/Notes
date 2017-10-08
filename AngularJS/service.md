* [1] CRUD for resources: http://www.sitepoint.com/creating-crud-app-minutes-angulars-resource/
* [2] http://blog.mgechev.com/2014/02/05/angularjs-resource-active-record-http/
* [3] http://www.tothenew.com/blog/angulars-resource-for-crud-operations/
* [4] angular resource tutorial: https://www.sitepoint.com/creating-crud-app-minutes-angulars-resource/

AngularJS guide on services.
* lazy instantiated: only created when other component depends on it.
* singleton: shared by all the components.
usually created by using service factory function. you can also register service by using $provide in your factory functionn:
```javascript
angular.module('myModule', []).config(['$provide', function($provide) {
  $provide.factory('serviceId', function() {
    var shinyNewServiceInstance;
    // factory function body that constructs shinyNewServiceInstance
    return shinyNewServiceInstance;
  });}]);
```
Promises
in angularJS we use service $q to do the promises which help us implement a way to execute asynchronous functions in series by registering them with a promise object.
```javascript
.run(function(getData) {
  var promise = getData()
    .then(function(string) {
      console.log(string) }
```
Using the then() method, attach a callback function to the returned promise object that prints out the returned string The second parameter of the then() method is an optional error handling callback function that'll be called if and only if the promise is rejected.

One of the guarantees promises make is that either the success or the error callback will be invoked, but never both. What happens if you need to ensure a specific function executes regardless of the result of the promise? You can do this by registering that function on the promise using the finally() method. This can be really useful for reseting code to a known state.

you can chain multiple requests to the promise to make it implement multiple async function calls in orders: 
```javascript 
.run(function(getData) {
  var promise = getData()
    .then(function(num) {
      console.log(num)
      return num * 2
    })
    .then(function(num) {
      console.log(num) // = random number * 2
    })
})
```
Promise is playing an important role in ES6 specifications in the js world.

How to use http to do the get request with path variable in angular?
```javascript
     var getStatus = function(mrn) {
        console.log("mrn is "+mrn);
        return $http({
          url: apiBase+'patient/'+mrn,
          method: 'GET'
          })
          .success(function(results) {
            return results;
          })
          .error(function(error) {
            console.log('an error occured', error);
          });
      };
```
More information can be referred in the certain_m project: https://codenvy.com/ws/finalfanleisy/certainm  (this example needed to be updated to use new http().then methods)

how to share data between different controllers?
by using service: http://stackoverflow.com/questions/21919962/angular-share-data-between-controllers

A better way to use http service in angular: use http().then() to replace the deprecated http.get().success():
```javascript
(function(angular) {
  'use strict';
angular.module('httpExample', [])
  .controller('FetchController', ['$scope', '$http', '$templateCache',
    function($scope, $http, $templateCache) {
      $scope.method = 'GET';
      $scope.url = 'http-hello.html';
      $http({
  method: 'GET',
  url: 'http://runnerp12.codenvycorp.com:57410/polls/1'
}).then(function successCallback(response) {
   alert(response.headers('Content-Type'));
  }, function errorCallback(response) {
    alert("errors");
  });

      $scope.updateModel = function(method, url) {
        $scope.method = method;
        $scope.url = url;
      };
    }]);
})(window.angular);
```

Reference: 

http://stackoverflow.com/questions/22064288/angularjs-http-response-header
http://plnkr.co/edit/NQ3b2BuNpXHit2tjzuZI

how to add bearer header in angular http calls for Oauth2 authentication?

use http interceptor: http://stackoverflow.com/questions/23244809/angular-js-set-token-on-header-default
then you don’t need to add headers in every request. 
