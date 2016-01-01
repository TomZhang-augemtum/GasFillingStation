app && app.controller('setting', function($scope, $http) {
  $.get("/api/setting/list").success(function(data){
    $scope.$apply(function(){
      $scope.setting = data;
    })
  })
  $scope.update = function() {
    console.log($scope.setting);
    $.post('/api/setting/update', {
      'EncodingAESKey': $scope.setting.EncodingAESKey,
      'agentid': $scope.setting.agentid,
      'corpid': $scope.setting.corpid,
      'domain': $scope.setting.domain,
      'Token': $scope.setting.Token,
      'secret': $scope.setting.secret
    }).success(function(data){
      console.log(data);
    })
  }
  $scope.backup = function() {
    $.get("/api/dataset/backup").success(function(data){
      alert('备份成功');
    })
  }
  $scope.restore = function() {
    $.get("/api/dataset/restore").success(function(data){
      alert('恢复成功');
    })
  }
})
