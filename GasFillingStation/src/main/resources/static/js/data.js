app && app.controller("data", function($scope, $http){
  $scope.company = {};
  $scope.employee = {};
  $scope.currentTag = "company";
  $scope.company.currentPage = 1;
  $scope.company.pageSize = 20;
  $scope.company.loadData = function() {
    $.get("/api/sale/company",{
      'orderColumn':"gas",
      'order': 'desc',
      'fromDate': new Date(new Date().setMonth(new Date().getMonth() - 1)),
      'toDate': new Date(),
      'page': $scope.company.currentPage - 1,
      'size': $scope.company.pageSize
    },function(data){
      $scope.$apply(function(){
        console.log(data);
        $scope.company.totalPages = data.totalPages;
        $scope.company.saleDatas = data.content;
      })
    })
  }
  
  $scope.company.gotoPage = function(page) {
    $scope.company.currentPage = page;
    $scope.company.loadData();
  }
  
  $scope.company.changeRowNums = function(num) {
    $scope.company.pageSize = num;
    $scope.company.loadData();
  }
  $scope.company.showEmployee = function(id, name) {
    $scope.currentTag = "employee";
    $scope.employee.company = name;
  }
  $scope.company.init = function() {
    $scope.company.loadData();
  }
  $scope.employee.init = function() {
    $scope.employee.loadData();
  }
  $scope.employee.loadData = function() {
    $.get("/api/sale/employee",{
      'orderColumn':"gas",
      'order': 'desc',
      'fromDate': new Date(new Date().setMonth(new Date().getMonth() - 1)),
      'toDate': new Date(),
      'page': $scope.company.currentPage - 1,
      'size': $scope.company.pageSize,
      'companyid': 1
    },function(data){
      $scope.$apply(function(){
        $scope.employee.saleDatas = data.content;
        $scope.employee.totalPages = data.totalPages;
      })
    })
  }
  $scope.closeEmployeeList = function() {
    $scope.currentTag = "company";
  }
  $scope.$watch('currentTag', function(val){
    $scope[val].init && $scope[val].init();
  })
})