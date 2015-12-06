window.localStorage.setItem('more',"MORE");
window.localStorage.setItem('navigation','HOME');

app.controller("user", function($scope,$http){
    $http.get("/api/user/list").success(function(data){
        console.log(data);
    })
})