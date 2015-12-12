var app;
app || function(){
  app = angular.module('gas', []);
  app.directive('pagenation',function(){
    return {
      templateUrl: '/templates/pagenation.html',
      replace: true,
      restrict: 'EA',
      scope: {
        goPage: "&",
        totalPages: "=",
        changeSize: "&"
      },
      link: function($scope) {
        console.log($scope.totalPages);
        $scope.pageSize = "20";
        $scope.currentPage = 1;
        $scope.changePageSize = function() {
          $scope.currentPage = 1;
          $scope.changeSize({'num':$scope.pageSize});
        }
        $scope.turnPage = function(str) {
          switch(str) {
            case "next":
              ++$scope.currentPage > $scope.totalPages && --$scope.currentPage
              break;
            case "pre":
              --$scope.currentPage < 1 && ++$scope.currentPage
              break;
          }
          $scope.goPage({'page':$scope.currentPage});
        }
      }
    }
  });

}();
