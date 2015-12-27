app && app.controller('substation', function($scope, $http) {
  $scope.companys = [];
  $scope.currentPage = 1;
  $scope.pageSize = 20;
  $scope.sort = 'id desc';
  $scope.showAddSubStation = false;
  
  _loadCompanys = function() {
    $http.get("/api/company/list/Pagenation" + "?offset=" + ($scope.currentPage - 1) + "&limit=" + $scope.pageSize + "&sort=" + $scope.sort).success(function(data){
      $scope.totalPages = data.totalPages;
      $scope.companys = data.content;
      $scope.totalCount = data.totalElements;
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
  $scope.addSubStationPopup = function() {
    $scope.addSubStation = {};
    $scope.showAddSubStation = true;
  }
  $scope.closeaddSubStationPopup = function() {
    $scope.showAddSubStation = false;
  }
  $scope.submit = function() {
    $.post("/api/company/save",{
      "id": $scope.addSubStation.id,
      "name": $scope.addSubStation.name,
      "location": $scope.addSubStation.location,
      "leadingUser": $scope.addSubStation.leadingUser,
      "freeGasNumber": $scope.addSubStation.freeGasNumber
    }).success(function(data){
      $scope.showAddSubStation = false;
      _loadCompanys();
    })
  }
  $scope.reset = function() {
    $scope.addSubStation = {};
  }
  $scope.deleteCompany = function(id) {
    $.get("/api/company/delete",{
      "id": id
    }).success(function(data){
      _loadCompanys();
    })
  }
  $scope.editCompany = function(id) {
    $scope.companys.forEach(function(company){
      company.id == id && ($scope.addSubStation = company);
    })
    $scope.showAddSubStation = true;
  }
  _loadCompanys();
})
