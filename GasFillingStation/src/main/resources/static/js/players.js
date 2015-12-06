app && app.controller('players', function($scope) {
  $scope.palyersPage = 'detail';
  $scope.lists = [];
  for (var i = 0; i <= 6; i++) {
    $scope.lists.push(i);
  }
  $scope.changePage = function() {
    $scope.palyersPage = 'main';
  }
})
