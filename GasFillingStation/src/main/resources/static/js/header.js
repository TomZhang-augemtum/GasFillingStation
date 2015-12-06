app && app.controller('header', function($scope) {
  $scope.showHideTitle = false;
  $scope.showAddList = false;
  $scope.navigation = window.localStorage.getItem('navigation') || "HOME";
  $scope.more = window.localStorage.getItem('more') || "MORE";
  $scope.toggleAddList = function() {
    $scope.showAddList = !$scope.showAddList;
    $scope.showAddList && $(".add-list").css("left", $('#header').offset().left + parseInt($('#header').css('width')) - 210),
    $(".add-list").css("top",1 + $('#header').offset().top + parseInt($('#header').css('height')));
  }
  $scope.toggleTitle = function() {
    $scope.showHideTitle = !$scope.showHideTitle;
    $scope.showHideTitle && $(".hide-title").css("left", $(".hide-title").parent().offset().left),
    $(".hide-title").css("top",1 + $('#header').offset().top + parseInt($('#header').css('height')));
   }
  $('.navigation').find('a').each(function(){
    $(this).on('click',function() {
      if (typeof $(this).parent().attr('ng-class') == "undefined") {
        window.localStorage.setItem('navigation',"MORE");
        window.localStorage.setItem('more',$(this).html());
      } else {
        window.localStorage.setItem('more',"MORE");
        window.localStorage.setItem('navigation',$(this).html());
      }
    })
  })
  $('.add-list').find('a').each(function() {
    $(this).on('click', function(){
      window.localStorage.setItem('navigation',$(this).attr('title'));
    })
  })
});