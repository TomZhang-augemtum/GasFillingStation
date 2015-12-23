app && app.controller('card', function($scope, $http) {
  $scope.currentPage = 1;
  $.get('/wx/card/currentcustomer').success(function(data){
    $scope.$apply(function(){
      $scope.card = data;
      console.log($scope.card);
    })
  }) 
  $scope.currentTag = 'info';
  $scope.toggleWidth = function(eve) {
    $(eve.currentTarget).toggleClass('small-qrcode')
  }
  $scope.catHistory = function() {
    $scope.currentTag = 'history';
    _loadHistory();
  }
  $scope.showInfo = function() {
    $scope.currentTag = 'info';
  }
  _loadHistory = function() {
    $.get('/wx/card/customer/history?page='+($scope.currentPage-1)).success(function(data){
      $scope.$apply(function(){
        $scope.historys = data.content;
        $scope.totalPages = data.totalPages;
        $scope.historys.forEach(function(history){
          history.type=='cost' && (history.money = '-'+history.money);
          history.type=='recharge' && (history.money = '+'+history.money);
        })
      })
    })
  }
  $scope.turnToPage = function(page) {
    $scope.currentPage = page;
    $scope.currentPage = $scope.currentPage > $scope.totalPages ? $scope.totalPages : $scope.currentPage;
    $scope.currentPage = $scope.currentPage < 1 ? 1 : $scope.currentPage;
    _loadHistory();
  }
}) 