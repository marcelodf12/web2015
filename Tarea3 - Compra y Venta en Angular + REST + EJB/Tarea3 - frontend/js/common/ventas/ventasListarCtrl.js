App.controller('ventasListarCtrl', function($scope, $http, $location) {
    $scope.ventas = [];
    $scope.parametros= {page:1};
    $scope.numOrd= true;
    $scope.monOrd= true;
    $scope.nomOrd= true;
    $scope.rucOrd= true;
    $scope.fecOrd= true;
    
    $scope.peticion= function(parametros){
        //PETICION DEL RECURSO
        $http({
            url: "http://localhost:8080/Tarea3-web/ventas",
            method: "GET", 
		    params: parametros
		}).then(function(response){
            $scope.ventas= response.data.data;
            $scope.paginacion= response.data.meta;
        }, function(response){
            //ERROR
        });
    };
    //BUSQUEDAS
    $scope.busquedaGeneral = function(filtro){
        $scope.filtroPorCategorias= undefined;
        $scope.parametros= {};
        if (angular.isDefined(filtro) && filtro!= ""){
            $scope.parametros.data= {};
            if (!isNaN(Number(filtro))){
                $scope.parametros.data.numero= filtro;
                $scope.parametros.data.montoTotal= filtro;
            }
            $scope.parametros.data.nombreCliente= filtro;
            $scope.parametros.data.cliente={
                ruc: filtro
            } 
        }
        $scope.parametros.page= 1;
        $scope.peticion($scope.parametros);
    };
    $scope.buscarPorCategorias = function(categoria, filtro){
        $scope.filtro= undefined;
        $scope.parametros= {};
        $scope.parametros.page= 1;
        if (angular.isDefined(filtro) && filtro!= ""){
            $scope.parametros.data= {};
            switch(categoria) {
                case "2":
                    $scope.parametros.data.montoTotal= filtro;
                    break;
                case "3":
                    $scope.parametros.data.nombreCliente= filtro;
                    break;
                case "4":
                    $scope.parametros.data.cliente={
                        ruc: filtro
                    }
                    break;
                default:
                    $scope.parametros.data.numero= filtro;
            }
        }
        $scope.peticion($scope.parametros);
    };
    $scope.ordenar = function(orderBy, orderDir){
        $scope.parametros.page= 1;
        switch(orderBy) {
            case "2":
                $scope.parametros.orderBy= "MONTO_TOTAL";
                if ($scope.monOrd){
                    $scope.parametros.orderDir= "ASC";
                }else{
                    $scope.parametros.orderDir= "DESC"
                }
                $scope.monOrd= !$scope.monOrd;
                break;
            case "3":
                $scope.parametros.orderBy= "NOMBRE_CLIENTE";
                if ($scope.nomOrd){
                    $scope.parametros.orderDir= "ASC";
                }else{
                    $scope.parametros.orderDir= "DESC"
                }
                $scope.nomOrd= !$scope.nomOrd;
                break;
            case "4":
                $scope.parametros.orderBy= "RUC_CLIENTE";
                if ($scope.rucOrd){
                    $scope.parametros.orderDir= "ASC";
                }else{
                    $scope.parametros.orderDir= "DESC"
                }
                $scope.rucOrd= !$scope.rucOrd;
                break;
            case "5":
                $scope.parametros.orderBy= "FECHA";
                if ($scope.fecOrd){
                    $scope.parametros.orderDir= "ASC";
                }else{
                    $scope.parametros.orderDir= "DESC"
                }
                $scope.fecOrd= !$scope.fecOrd;
                break;
            default:
                $scope.parametros.orderBy= "NUMERO";
                if ($scope.numOrd){
                    $scope.parametros.orderDir= "ASC";
                }else{
                    $scope.parametros.orderDir= "DESC"
                }
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
        alert("Datos de la Venta:\n - Numero: " + x.numero + "\n - Monto Total: " + x.montoTotal + "\n - Nombre Cliente: " + x.nombreCliente + "\n - RUC Cliente: " + x.cliente.ruc + "\n - Fecha: " + x.fecha);
    };
    //Listado General
    $http({
        url: "http://localhost:8080/Tarea3-web/ventas",
        method: "GET"
    }).then(function(response){
		$scope.ventas= response.data.data;
		$scope.paginacion= response.data.meta;
    }, function(response){
        //ERROR
    });

    $scope.exportar= function(metodo){
        $scope.parametros.metodo= metodo;
        $http({
            url: "http://localhost:8080/Tarea3-web/ventas/exportar",
            method: "POST", 
            params: $scope.parametros
        }).then(function(response){
            //exito
        }, function(response){
            //ERROR
        });
    }

});
