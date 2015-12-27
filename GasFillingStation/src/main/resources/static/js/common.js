var app;
app || function(){
  app = angular.module('gas', []);
  app.directive('pagenation',function(){
    return {
      templateUrl: '/templates/pagenation.html',
      replace: true,
      restrict: 'EA',
      scope: {
        goPage: "&",
        totalPages: "=",
        changeSize: "&"
      },
      link: function($scope) {
        $scope.pageSize = "20";
        $scope.currentPage = 1;
        $scope.changePageSize = function() {
          $scope.currentPage = 1;
          $scope.changeSize({'num':$scope.pageSize});
        }
        $scope.turnPage = function(str) {
          switch(str) {
            case "next":
              ++$scope.currentPage > $scope.totalPages && --$scope.currentPage
              break;
            case "pre":
              --$scope.currentPage < 1 && ++$scope.currentPage
              break;
          }
          $scope.goPage({'page':$scope.currentPage});
        }
      }
    }
  }).directive('repeatDone', function($rootScope, $timeout){
    return {
      restrict: 'C',
      link: function(scope, element, attrs){
        scope.$last && 
        $timeout(function(){
          scope.$emit('ngRepeatFinished');
        });
      }
    }
  }).directive('chart',function(){
    return {
      template: '<canvas id="myChart"></canvas>',
      replace: true,
      restrict: 'EA',
      scope: {
        chartData:'='
      },
      link: function($scope) {
        var chart;
        $scope.$watch('chartData',function(val){
          val && (chart =(function(){
            chart && chart.destroy();
            var data = $scope.chartData;
            var options = {
                //Boolean - If we show the scale above the chart data     
                scaleOverlay : false,
                //Boolean - If we want to override with a hard coded scale
                scaleOverride : false,
                //** Required if scaleOverride is true **
                //Number - The number of steps in a hard coded scale
                scaleSteps : null,
                //Number - The value jump in the hard coded scale
                scaleStepWidth : null,
                //Number - The scale starting value
                scaleStartValue : null,
                //String - Colour of the scale line 
                scaleLineColor : "rgba(0,0,0,.1)",
                //Number - Pixel width of the scale line  
                scaleLineWidth : 1,
                //Boolean - Whether to show labels on the scale 
                scaleShowLabels : true,
                //Interpolated JS string - can access value
                scaleLabel : "<%=value%>",
                //String - Scale label font declaration for the scale label
                scaleFontFamily : "'Arial'",
                //Number - Scale label font size in pixels  
                scaleFontSize : 12,
                //String - Scale label font weight style  
                scaleFontStyle : "normal",
                //String - Scale label font colour  
                scaleFontColor : "#666",  
                ///Boolean - Whether grid lines are shown across the chart
                scaleShowGridLines : true,
                //String - Colour of the grid lines
                scaleGridLineColor : "rgba(0,0,0,.05)",
                //Number - Width of the grid lines
                scaleGridLineWidth : 1, 
                //Boolean - Whether the line is curved between points
                bezierCurve : true,
                //Boolean - Whether to show a dot for each point
                pointDot : false,
                //Number - Radius of each point dot in pixels
                pointDotRadius : 0,
                //Number - Pixel width of point dot stroke
                pointDotStrokeWidth : 1,
                //Boolean - Whether to show a stroke for datasets
                datasetStroke : true,
                //Number - Pixel width of dataset stroke
                datasetStrokeWidth : 2,
                //Boolean - Whether to fill the dataset with a colour
                datasetFill : true,
                //Boolean - Whether to animate the chart
                animation : true,
                //Number - Number of animation steps
                animationSteps : 60,
                //String - Animation easing effect
                animationEasing : "easeOutQuart",
                //Function - Fires when the animation is complete
                onAnimationComplete : function() {
                }
              }
            var canvas = $("#myChart");
            var ctx = canvas.get(0).getContext("2d");
            ctx.canvas.width = parseInt(canvas.parent().css('width'));
            var myChart = new Chart(ctx).Line(data,options);
            $(window).on('resize', function(event) {
              myChart.destroy();
              ctx.canvas.width = parseInt(canvas.parent().css('width'));
              myChart = new Chart(ctx).Line(data,options);
            })
            return myChart;
          })());
        })
      }
    }
  });
}();
