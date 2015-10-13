App.controller('solicitudListarCtrl', function($scope, $http, $location) {
    $scope.solicitudes = [];
    //Listado General
    $http({
        url: "http://localhost:8080/Tarea3-web/solicitudesdecompras",
        method: "GET"
    }).then(function(response){
		$scope.solicitudes= response.data.data;
    }, function(response){
        //ERROR
    });
});