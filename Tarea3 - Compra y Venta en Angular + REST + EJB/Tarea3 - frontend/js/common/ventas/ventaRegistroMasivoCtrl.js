App.controller('ventaRegistroMasivoCtrl', function($scope, $http, $location) {
	$scope.enviar=function() {
			var file = $scope.csv;
			var fd = new FormData();
			fd.append('file', file);
			$http.post('http://localhost:8080/Tarea2-web/compras/file', fd, {
				transformRequest: angular.identity, 
				headers: {'Content-Type': undefined}
				})
				.success(function(response){
					console.log("Exito");
				})
				.error(function(response){
					console.log("Error");
			});
			}
});
