(function(){
  var result = true;
  var validateType = {
      notEmpty: function(value) {
        var flag = true;
        (value.length==0) && (flag = false);
        return flag && determineLength(arguments);
      },
      email: function(value) {
        var flag = true;
        var reg =  /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        flag = reg.test(value) && determineLength(arguments);
        return flag;
      },
      number: function(value) {
        return !isNaN(value) && determineLength(arguments);
      }
  }
  function determineLength(arg) {
    var flag = true;
    var args = Array.prototype.slice.call(arg, 0);
    var value = args[0];
    (args.length == 2) && ((value + '').length != args[1]) && (flag = false);
    (args.length == 3) && !(
        (value.length >= args[1] && value.length >= args[2])
        || (value.length >= args[2] && value.length >= args[1])
    ) && (flag = false); 
    return flag;
  }
  function validate(ele) {
    var flag = true;
    ele.val(ele.val().replace(/(^\s*)|(\s*$)/g, ''));
    if (!ele.val().length){
      return false;
    } 
    if (validateType[ele.attr('require')]) {
      validateType[ele.attr('require')](ele.val()) || (flag=false);
    } else if (ele.attr('require').indexOf('(') != -1){
      if(!ele.val()) {
        flag = false;
      } else {
        var type = ele.attr('require').replace(/([^\(]*\()/g,'$1' + ele.val() + ',');
        flag = eval('validateType.'+type);
      }
    } else {
      flag = eval(ele.attr('data-type')).test(ele.val());
    }
    return flag;
  }
  function validation(ele) {
    result = true;
    $(ele).find("[require]").each(function(){
      $(this).removeClass('validation-error');
      var flag = validate($(this));
      (flag || ($(this).addClass('validation-error'), function(ele){
        ele.one('blur', function() {
          validate(ele) && ele.removeClass('validation-error');
        });
      }($(this))));
      result = result && flag;
    })
  }
  validation.prototype = {
      success: function(fn) {
        result && fn && fn();
        return this;
      },
      error: function(fn) {
        !result && fn && fn();
        return this;
      }
  }
  window.validation = function(ele) {
    return new validation(ele);
  }
})();