var app= angular.module("tarea3", []);

app.controller("controlador", function($scope){
    $scope.ventas = [];
    $scope.parametros= {page:1};
    $scope.numOrd= true;
    $scope.monOrd= true;
    $scope.nomOrd= true;
    $scope.rucOrd= true;
    $scope.fecOrd= true;
});

app.directive("midirectiva", function($http){
	return{
		restrict: 'C', 
		controller: ['$scope', '$http', function($scope, $http) {
            $scope.peticion= function(parametros){
                //PETICION DEL RECURSO
                $http({
                    url: "http://localhost:8080/Tarea3-web/ventas",
                    method: "GET", 
				    params: parametros
				}).then(function(response){
                    $scope.ventas= response.data.ventas;
                    $scope.paginacion= response.data.meta;
                }, function(response){
                    //ERROR
                });
            },
            //BUSQUEDAS
            $scope.busquedaGeneral = function(filtro){
                $scope.filtroPorCategorias= undefined;
                $scope.parametros= {};
                $scope.parametros.by_all_attributes= filtro;
                $scope.parametros.page= 1;
                $scope.peticion($scope.parametros);
            };
            $scope.buscarPorCategorias = function(categoria, filtro){
                $scope.filtro= undefined;
                $scope.parametros= {};
                $scope.parametros.page= 1;
                switch(categoria) {
                    case "by_fecha":
                        $scope.parametros.by_fecha= filtro;
                        break;
                    case "by_monto_total":
                        $scope.parametros.by_monto_total= filtro;
                        break;
                    case "by_nombre_cliente":
                        $scope.parametros.by_nombre_cliente= filtro;
                        break;
                    case "by_ruc_cliente":
                        $scope.parametros.by_ruc_cliente= filtro;
                        break;
                    default:
                        $scope.parametros.by_numero= filtro;
                }
                $scope.peticion($scope.parametros);
            };
            //ORDENACION
            $scope.ordenar = function(orderBy, orderDir){
                $scope.parametros.page= 1;
                switch(orderBy) {
                    case "fecha":
                        $scope.parametros.numero = undefined;
                        $scope.parametros.monto_total= undefined;
                        $scope.parametros.nombre_cliente= undefined;
                        $scope.parametros.ruc_cliente= undefined;
                        $scope.parametros.fecha= orderDir;
                        $scope.fecOrd= !$scope.fecOrd;
                        break;
                    case "monto_total":
                        $scope.parametros.numero = undefined;
                        $scope.parametros.fecha= undefined;
                        $scope.parametros.nombre_cliente= undefined;
                        $scope.parametros.ruc_cliente= undefined;
                        $scope.parametros.monto_total= orderDir;
                        $scope.monOrd= !$scope.monOrd;
                        break;
                    case "nombre_cliente":
                        $scope.parametros.numero = undefined;
                        $scope.parametros.monto_total= undefined;
                        $scope.parametros.fecha= undefined;
                        $scope.parametros.ruc_cliente= undefined;
                        $scope.parametros.nombre_cliente= orderDir;
                        $scope.nomOrd= !$scope.nomOrd;
                        break;
                    case "ruc_cliente":
                        $scope.parametros.numero = undefined;
                        $scope.parametros.monto_total= undefined;
                        $scope.parametros.nombre_cliente= undefined;
                        $scope.parametros.fecha= undefined;
                        $scope.parametros.ruc_cliente= orderDir;
                        $scope.rucOrd= !$scope.rucOrd;
                        break;
                    default:
                        $scope.parametros.fecha = undefined;
                        $scope.parametros.monto_total= undefined;
                        $scope.parametros.nombre_cliente= undefined;
                        $scope.parametros.ruc_cliente= undefined;
                        $scope.parametros.numero= orderDir;
                        $scope.numOrd= !$scope.numOrd;
                };
                $scope.peticion($scope.parametros);
            };
            //PAGINACION
            $scope.paginar= function(pagina){
                $scope.parametros.page= pagina;
                $scope.peticion($scope.parametros);
            };
            //POPUP DE DOBLE CLICK
            $scope.obtener = function(x){
                alert("Datos de la Venta:\n - Numero: " + x.numero + "\n - Monto Total: " + x.montoTotal + "\n - Nombre Cliente: " + x.nombreCliente + "\n - RUC Cliente: " + x.rucCliente + "\n - Fecha: " + x.fecha);
            };
            //Listado General
            $http({
                url: "http://localhost:8080/Tarea3-web/ventas",
                method: "GET"
            }).then(function(response){
				$scope.ventas= response.data.ventas;
				$scope.paginacion= response.data.meta;
            }, function(response){
                //ERROR
            });

		}]
	}
});