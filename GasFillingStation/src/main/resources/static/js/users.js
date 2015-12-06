app && app.controller('users', function($scope, $http) {
  $scope.users = [];
  $scope.currentPage = 1;
  $scope.pageSize = 20;
  $scope.sort = 'id desc';
  $scope.changeLeftNav = function(tag) {
    $scope.leftNav = tag;
    if (tag != "addUser") {
      $scope.category = tag;
      _loadUsers();
    } 
  }
  _loadUsers = function() {
    var offset = ($scope.currentPage - 1) * $scope.pageSize;
    var limit = $scope.pageSize;
    console.log(offset);
    console.log(limit);
    $http.get("/api/" + $scope.category +"/list/Pagenation" + "?offset=" + offset + "&limit=" + limit + "&sort=" + $scope.sort).success(function(data){
      $scope.users = data;
      console.log(data);
    })    
    $http.get("/api/" + $scope.category +"/list/count").success(function(data){
      $scope.totalCount = data;
    })    
  };
  $scope.changeSort = function(sort) {
    if ($scope.sort.indexOf(sort) != -1) {
      ($scope.sort.indexOf('desc') != -1) ? ($scope.sort = $scope.sort.replace("desc",'asc')):($scope.sort = $scope.sort.replace("asc",'desc'));
    } else {
      $scope.sort = sort + " desc";
    }
    _loadUsers();
  };
  $scope.gotoPage = function(page) {
    $scope.currentPage = page
    _loadUsers();
  };
  $scope.changeRowNums = function(num) {
    $scope.currentPage = 1;
    $scope.pageSize = num;
    _loadUsers();
  };
  
  $scope.changeLeftNav('customer');
})
