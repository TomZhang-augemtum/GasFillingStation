app && app.controller('permission', function($scope, $http) {
  $scope.currentPermission = 'menuPermission';
  $scope.currentPage = 1;
  $scope.pageSize = 20;
  $scope.order="asc";
  $scope.orderColumn = 'name';
  $scope.showChangeRole = false;
  $scope.showResult = false;
  $.get('/api/permission/list').success(function(data){
    console.log(data);
    $scope.$apply(function(){
      $scope.menus = data.menus;
      $scope.roles = data.roles;
    })
  })
  $scope.checkRoleHasMenu = function(role, menu){
    var flag = false;
    role.menus.forEach(function(roleMenu){
      roleMenu.id == menu.id && (flag = true);
    })
    return flag;
  }
  $scope.changePermission = function(role, menu) {
    $.post('/api/permission/update',{
      "roleid": role.id,
      "menuid": menu.id
    }).success(function(data){
      $scope.$apply(function(){
        $scope.showResult = true;
        setTimeout(function(){
          $scope.$apply(function(){
            $scope.showResult = false;
          });
        },2000);
      })
    })
  }
  $scope.showUserRoleManage = function(tag) {
    console.log(tag);
    $scope.currentPermission = tag;
    tag == 'userRoleManage' && _getUserRoleData();
  }
  _getUserRoleData = function() {
    $.get('/api/user/list', {
      'page': $scope.currentPage - 1,
      'size': $scope.pageSize,
      'order': $scope.order,
      'orderColumn': $scope.orderColumn
    }).success(function(data){
      $scope.$apply(function(){
        $scope.totalPages = data.totalPages;
        $scope.users = data.content;
      })
    })
  }
  $scope.gotoPage = function(page) {
    $scope.currentPage = page;
    _getUserRoleData();
  }
  $scope.changeRowNums = function(num) {
    $scope.pageSize = num;
    _getUserRoleData();
  }
  $scope.showChangeRolePage = function(user) {
    $scope.showChangeRole = true;
    $scope.currentChangeUser = user;
    $scope.currentChangeUser.role.id += '';
  }
  $scope.closeShowChangeRolePopup = function() {
    $scope.showChangeRole = false;
  }
  $scope.submitChangeRole = function() {
    $.post('/api/permission/userrole/update', {
      'userid': $scope.currentChangeUser.id,
      'roleid': $scope.currentChangeUser.role.id
    }).success(function(data){
      $scope.$apply(function(){
        $scope.showChangeRole = false;
        $scope.showResult = true;
        setTimeout(function(){
          $scope.$apply(function(){
            $scope.showResult = false;
          });
        },2000);
      })
    })
  }
})
