App.controller('solicitudListarCtrl', function($scope, $http, $location) {
    $scope.solicitudes = [];
    //Listado General
    $http({
        url: "http://localhost:8080/Tarea3-web/solicituddecompras",
        method: "GET"
    }).then(function(response){
		$scope.solicitudes= response.data;
    }, function(response){
        //ERROR
    });
});