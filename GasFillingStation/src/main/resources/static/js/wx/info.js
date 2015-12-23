app && app.controller('info', function($scope, $http) {
  $.get('/wx/customer/current/info').success(function(data){
    $scope.$apply(function(){
      $scope.user = data;
    }).error(function(data){
    })
  })
  
  $scope.submit = function() {
    validation('.customer-info').success(function(){
      var data = {
        'userid': $scope.user.id,
        'phone': $scope.user.phone
      }
      $.post('/wx/customer/change/phone',data).success(function(data){
        console.log(data);
      })
    })  
  }
})