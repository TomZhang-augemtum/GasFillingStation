app.controller('addkiosk', function($scope) {
  $scope.addKiosk = 'result';
  $scope.submitAddKiosk = function() {
    $scope.addKiosk = 'result';
  }
  $scope.addAnother = function() {
    $scope.addKiosk = 'setting';
  }
})