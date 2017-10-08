```javascript
self.people = [
      {
        name: "Eric Simons",
        born: "Chicago"
      }
    ];
```
```html
<body ng-app="app" ng-controller="TestCtrl as test">
  <input type="text" ng-model="search" />
  <p ng-repeat="person in test.people | filter: search">
    {{ person.name }}
  </p>
</body>
```
By default, this will search all attributes in the people objects (currently that includes name and born). 
You can have it search specific attributes by dotting the field name in the model like so:
```html
<input type="text" ng-model="search.name" />
For a general search across all attributes, you can simply use $:
<input type="text" ng-model="search.$" />
```
Some useful built in filters can be viewed as followed: 
```
<p ng-repeat="person in test.people | filter:search | orderBy:'name'  | limitTo: 5">
```

You can write up your own customized filters by using following example: 
```javascript
angular.module('app', [])
.controller('TestCtrl', TestCtrl)
// define a filter called 'capitalize' that will invoke the CapitalizeFilter function
.filter('capitalize', CapitalizeFilter);

// this is where the filter magic happens.
function CapitalizeFilter() {
  // this is the function that Angular will execute when the expression is evaluated
  return function (text) {
    return text.toUpperCase();
  }
}

function TestCtrl() {
  // basic controller where we preset the scope myString variable
  var self = this;
  self.myString = "hello world";
}
```
Then in your html file you can use your customized filter as followed: 
```html
<input type="text" ng-model="test.myString" />
<h2>
  {{ test.myString | capitalize }}
</h2>
```
How to use ng-if?
http://stackoverflow.com/questions/20305012/angularjs-ng-if-with-multiple-conditions


