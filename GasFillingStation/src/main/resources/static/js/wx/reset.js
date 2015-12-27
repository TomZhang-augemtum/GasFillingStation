app && app.controller('reset', function($scope, $http) {
    $.get('/wx/customer/current/info').success(function(data){
        $scope.$apply(function(){
          $scope.user = data;
        });
    }).error(function(data){
    })
    $scope.submit = function(){
        validation($('.reset')).success(function(){
            if($scope.password == $scope.passwordagain){
                $.get('/wx/user/change/password',{
                    'username': $scope.user.name,
                    'password': $scope.password
                }).success(function(data){
                    $scope.$apply(function(){
                        alert('操作成功，请牢记您的新密码');
                    });
                }).error(function(data){
                })
            }  else {
                alert('两次密码不一致');
            }
        })
    }
}) 