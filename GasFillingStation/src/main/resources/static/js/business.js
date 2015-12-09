app && app.controller('business', function($scope, $http) {
  $scope.currentBusiness = 'cost';
  $scope.faka = {};
  $scope.cost = {};
  $scope.recharge = {};
  $scope.guashi = {};
  $scope.jihuo = {};
  $scope.cardlist = {};
  
  $scope.changeBusiness = function(tag) {
    $scope.currentBusiness = tag;
    $scope[tag].init && $scope[tag].init();
  }
  
  $scope.faka.init = function() {
    $.get("/api/cartype/list").success(function(data){
      $scope.$apply($scope.faka.cartypes = data);
    })
  }

  $scope.faka.submit = function() {
    console.log($scope.faka.car.typeid);
    $.post("/api/card/save",{
      "name": $scope.faka.user.name,
      "phone": $scope.faka.user.phone,
      "cardid": $scope.faka.user.cardid,
      "carNumber": $scope.faka.car.number,
      "type.id": $scope.faka.car.typeid
    }).success(function(data){
    })
  }
  
  $scope.cardlist.init = function() {
    $.get("/api/card/list/pagenation").success(function(data){
      $scope.$apply($scope.cardlist.cards = data.content);
      console.log($scope.cardlist.cards);
    })
  }
})
