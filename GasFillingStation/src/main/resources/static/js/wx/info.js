app && app.controller('info', function($scope, $http) {
  $.get('/api/user/current/info').success(function(data){
    console.log(data);
    $scope.$apply(function(){
      $scope.user = data;
    })
  })
  
  $scope.submit = function() {
    validation('.customer-info').success(function(){
      var data = {
        'userid': $scope.user.id,
        'phone': $scope.user.phone
      }
      $.post('/api/user/change/phone',data).success(function(data){
        console.log(data);
      })
    })  
  }
})