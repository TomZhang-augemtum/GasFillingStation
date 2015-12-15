app && app.controller('wxbusiness', function($scope, $http) {
  $scope.submit = function() {
    $.post("/api/jssdk/config",{
      "url":window.location.href
    }, function(data) {
      console.log(data);
    });
  }
})