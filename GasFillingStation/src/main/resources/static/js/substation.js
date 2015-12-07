app && app.controller('substation', function($scope, $http) {
  $scope.companys = [];
  $scope.currentPage = 1;
  $scope.pageSize = 20;
  $scope.sort = 'id desc';
  $scope.showAddUser = false;
  
  _loadCompanys = function() {
    var offset = ($scope.currentPage - 1) * $scope.pageSize;
    var limit = $scope.pageSize;
    console.log(offset);
    console.log(limit);
    $http.get("/api/company/list/Pagenation" + "?offset=" + offset + "&limit=" + limit + "&sort=" + $scope.sort).success(function(data){
      $scope.companys = data;
      console.log(data);
    })    
    $http.get("/api/company/list/count").success(function(data){
      $scope.totalCount = data;
    })    
  };
  $scope.changeSort = function(sort) {
    if ($scope.sort.indexOf(sort) != -1) {
      ($scope.sort.indexOf('desc') != -1) ? ($scope.sort = $scope.sort.replace("desc",'asc')):($scope.sort = $scope.sort.replace("asc",'desc'));
    } else {
      $scope.sort = sort + " desc";
    }
    _loadCompanys();
  };
  $scope.gotoPage = function(page) {
    $scope.currentPage = page
    _loadCompanys();
  };
  $scope.changeRowNums = function(num) {
    $scope.currentPage = 1;
    $scope.pageSize = num;
    _loadCompanys();
  };
  $scope.showAddUserPopup = function() {
    $scope.adduser = {};
    $scope.showAddUser = true;
  }
  $scope.closeAddUserPopup = function() {
    $scope.showAddUser = false;
  }
  $scope.saveEmployee = function() {
    $.post("/api/company/save",{
      "id": $scope.adduser.id,
      "name": $scope.adduser.name,
      "number": $scope.adduser.number,
      "phone": $scope.adduser.phone,
      "cardid": $scope.adduser.cardid,
      "company.id": $scope.adduser.company.id
    }).success(function(data){
      $scope.showAddUser = false;
      _loadCompanys();
    })
  }
  $scope.resetEmployee = function() {
    $scope.adduser = {};
  }
  $scope.deleteUser = function(id) {
    $.get("/api/company/delete",{
      "id": id
    }).success(function(data){
      _loadCompanys();
    })
  }
  $scope.editUser = function(id) {
    $scope.companys.forEach(function(user){
      user.id == id && ($scope.adduser = user);
    })
    $scope.adduser.company.id += "";
    $scope.showAddUser = true;
  }
  _loadCompanys();
})
