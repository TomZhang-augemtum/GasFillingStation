app && app.controller('header', function($scope) {
  $('.navigation').find('a').each(function() {
    if($(this).attr('href')==window.location.pathname) {
      $(this).parent().addClass("active");
    } else {
      $(this).parent().removeClass("active");
    }
  })
});