app && app.controller("data", function($scope, $http){
  $.get("/api/sale/company",{
    'orderColumn':"gas",
    'order': 'desc',
    'fromDate': new Date(new Date().setMonth(new Date().getMonth() - 1)),
    'toDate': new Date(),
    'page': 0,
    'size': 20
  },function(data){
    $scope.$apply(function(){
      $scope.saleDatas = data;
    })
  })
})