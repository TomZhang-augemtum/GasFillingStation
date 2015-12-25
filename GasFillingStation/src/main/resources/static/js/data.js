app && app.controller("data", function($scope, $http){
  $scope.company = {};
  $scope.employee = {};
  $scope.currentTag = "company";
  $scope.company.currentPage = 1;
  $scope.company.pageSize = 20;
  $scope.company.orderColumn = "gas";
  $scope.company.order = "desc";
  $scope.showTimePicker = false;
  $scope.company.loadData = function() {
    var endDate = $scope.company.endDate || new Date();
    var beginDate = $scope.company.beginDate || new Date(new Date().setMonth(new Date().getMonth() - 1));
    $.get("/api/sale/company",{
      'orderColumn':$scope.company.orderColumn,
      'order': $scope.company.order,
      'fromDate': beginDate,
      'toDate': endDate,
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
  $scope.company.changeSort = function(col) {
    col == $scope.company.orderColumn && ($scope.company.order == "desc"? $scope.company.order = "asc":$scope.company.order = "desc");
    $scope.company.orderColumn = col;
    $scope.company.loadData();
  }
  $scope.company.changeData = function() {
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
  
  $scope.employee.currentPage = 1;
  $scope.employee.pageSize = 20;
  $scope.employee.orderColumn = "gas";
  $scope.employee.order = "desc";
  
  $scope.employee.init = function() {
    $scope.employee.loadData();
  }
  $scope.employee.loadData = function() {
    var endDate = $scope.employee.endDate || new Date();
    var beginDate = $scope.employee.beginDate || new Date(new Date().setMonth(new Date().getMonth() - 1));
    $.get("/api/sale/employee",{
      'orderColumn':$scope.employee.orderColumn,
      'order': $scope.employee.order,
      'fromDate': beginDate,
      'toDate': endDate,
      'page': $scope.employee.currentPage - 1,
      'size': $scope.employee.pageSize,
      'companyid': 1
    },function(data){
      $scope.$apply(function(){
        $scope.employee.saleDatas = data.content;
        $scope.employee.totalPages = data.totalPages;
      })
    })
  }
  
  $scope.employee.changeSort = function(col) {
    col == $scope.employee.orderColumn && ($scope.employee.order == "desc"? $scope.employee.order = "asc":$scope.employee.order = "desc");
    $scope.employee.orderColumn = col;
    $scope.employee.loadData();
  }
  $scope.employee.changeData = function() {
    $scope.employee.loadData();
  }
  
  $scope.closeEmployeeList = function() {
    $scope.currentTag = "company";
  }
  $scope.closeTimePickerPopup = function() {
    $scope.showTimePicker = false;
  }
  $scope.$watch('currentTag', function(val){
    $scope[val].init && $scope[val].init();
  })
  $scope.exportCompany = function(){
    $scope.showTimePicker = true;
  }
  $scope.submitCSV = function() {
    ($scope.beginDate && $scope.endDate || alert("请选择日期")) 
    && ($scope.beginDate - $scope.endDate <= 0 || alert("日期错误")) 
    && window.open(window.location.origin + '/api/sale/'+$scope.currentTag+'/csv?beginDate='+$scope.beginDate+'&endDate='+$scope.endDate);
  }
  $scope.exportEmployee = function(){
    $scope.showTimePicker = true;
  }
})