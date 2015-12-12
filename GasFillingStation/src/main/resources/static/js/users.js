app && app.controller('users', function($scope, $http) {
  $scope.users = [];
  $scope.currentPage = 1;
  $scope.pageSize = 20;
  $scope.sort = 'id desc';
  $scope.userType = 'customer';
  $scope.showAddUser = false;
  
  _loadUsers = function() {
    $.get("/api/employee/list/Pagenation" + "?offset=" + ($scope.currentPage -1) + "&limit=" + $scope.pageSize + "&sort=" + $scope.sort).success(function(data){
      $scope.$apply(function(){
        $scope.totalPages = data.totalPages;
        $scope.users = data.content;
      })
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
    $scope.adduser = {};
    $scope.showAddUser = true;
  }
  $scope.closeAddUserPopup = function() {
    $scope.showAddUser = false;
  }
  $scope.saveEmployee = function() {
    $.post("/api/employee/save",{
      "id": $scope.adduser.id,
      "name": $scope.adduser.name,
      "number": $scope.adduser.number,
      "phone": $scope.adduser.phone,
      "cardid": $scope.adduser.cardid,
      "company.id": $scope.adduser.company.id
    }).success(function(data){
      $scope.showAddUser = false;
      _loadUsers();
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
  $scope.editUser = function(id) {
    $scope.users.forEach(function(user){
      user.id == id && ($scope.adduser = user);
    })
    $scope.adduser.company.id += "";
    $scope.showAddUser = true;
  }
  _loadUsers();
  $http.get('/api/company/list').success(function(data){
    $scope.companys = data;
  })
})
