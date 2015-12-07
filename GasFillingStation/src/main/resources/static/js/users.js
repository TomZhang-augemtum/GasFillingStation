app && app.controller('users', function($scope, $http) {
  $scope.users = [];
  $scope.currentPage = 1;
  $scope.pageSize = 20;
  $scope.sort = 'id desc';
  $scope.userType = 'customer';
  $scope.showAddUser = false;
  
  _loadUsers = function() {
    var offset = ($scope.currentPage - 1) * $scope.pageSize;
    var limit = $scope.pageSize;
    console.log(offset);
    console.log(limit);
    $http.get("/api/employee/list/Pagenation" + "?offset=" + offset + "&limit=" + limit + "&sort=" + $scope.sort).success(function(data){
      $scope.users = data;
      console.log(data);
    })    
    $http.get("/api/employee/list/count").success(function(data){
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
  $scope.showAddUserPopup = function() {
    $http.get('/api/company/list').success(function(data){
      $scope.companys = data;
      $scope.showAddUser = true;
    })
  }
  $scope.closeAddUserPopup = function() {
    $scope.showAddUser = false;
  }
  $scope.saveEmployee = function() {
    $.post("/api/employee/save",{
      "name": $scope.adduser.name,
      "number": $scope.adduser.number,
      "phone": $scope.adduser.phone,
      "cardid": $scope.adduser.cardid,
      "companyid": $scope.adduser.companyid
    }).success(function(data){
      console.log("success");
    })
  }
  $scope.resetEmployee = function() {
    $scope.adduser = {};
  }
  $scope.deleteUser = function(id) {
    $.get("/api/user/delete",{
      "id": id
    }).success(function(data){
      _loadUsers();
    })
  }
  _loadUsers();
})
