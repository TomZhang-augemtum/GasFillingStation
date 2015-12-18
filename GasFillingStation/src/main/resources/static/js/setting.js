app && app.controller('setting', function($scope, $http) {
  $.get("/api/setting/list").success(function(data){
    $scope.$apply(function(){
      $scope.setting = data;
    })
  })
})
