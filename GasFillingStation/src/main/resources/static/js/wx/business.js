app && app.controller('wxbusiness', function($scope, $http) {
  $scope.price = 15;
  _configwxjs = function(data) {
    wx.config({
      debug: false,
      appId: data.appid,
      timestamp: parseInt(data.timestamp),
      nonceStr: data.nonceStr,
      signature: data.signature,
      jsApiList: ['scanQRCode']
    });
    wx.ready(function(){
      $scope.submit = function() {
        if (isNaN($scope.totalNum)){
          alert("数量不对")
        } else if(isNaN($scope.price)) {
          alert("价钱不对")
        } else {
          wx.scanQRCode({
            desc: 'scanQRCode desc',
            needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
            scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
            success: function (res) {
              var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
              var data = {
                  'gasAmount': $scope.totalNum,
                  'price': $scope.price,
                  'total': $scope.totalNum * $scope.price,
                  'cardid': result
              }
              $.post('/api/card/cost/scan',data).success(function(data){
                alert(data.message)
              })
            }
          });
        }
      }
    });
  }
  $.post("/api/jssdk/config",{
    "url":window.location.href
  }, function(data) {
    _configwxjs(data);
  });
  
  $scope.$watch('price', function(val) {
    $scope.totalPrice = val * $scope.totalNum;
  })
  $scope.$watch('totalNum', function(val) {
    $scope.totalPrice = val * $scope.price;
  })
})