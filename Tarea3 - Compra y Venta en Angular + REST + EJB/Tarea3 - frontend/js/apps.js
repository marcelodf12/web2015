/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var App = angular.module("App", ['ngRoute']);
App.config(['$routeProvider', function($routeProvider) {
        $routeProvider
                .when('/cliente/alta', {
                    templateUrl: 'cliente/alta.html',
                    controller: 'clienteAltaCtrl'
                })
                .when('/cliente/listar', {
                    templateUrl: 'cliente/listado.html',
                    controller: 'clienteListarCtrl'
                })
                .when('/cliente/registroMasivo', {
                    templateUrl: 'cliente/registroMasivo.html',
                    controller: 'clienteRegistroMasivoCtrl'
                })

                .when('/proveedor/alta', {
                    templateUrl: 'proveedores/alta.html',
                    controller: 'proveedorAltaCtrl'
                })
                .when('/proveedor/listar', {
                    templateUrl: 'proveedores/listado.html',
                    controller: 'proveedorListarCtrl'
                })


                .when('/producto/alta', {
                    templateUrl: 'productos/alta.html',
                    controller: 'productoAltaCtrl'
                })
                .when('/producto/listar', {
                    templateUrl: 'productos/listado.html',
                    controller: 'productoListarCtrl'
                })
                
                .when('/ventas/listar', {
                    templateUrl: 'ventas/listar.html',
                    controller: 'ventasListarCtrl'
                })
                
                .when('/ventas/registroMasivo', {
                    templateUrl: 'ventas/registroMasivo.html',
                    controller: 'registroMasivoCtrl'
                })

                .otherwise({RedirectTo: '/'});
    }]);


/* CRUD de Clientes
App.controller('clienteAltaCtrl', function($scope, $http, $location) {

});
App.controller('clienteListarCtrl', function($scope, $http) {

});


// CRUD de Proveedores
App.controller('proveedorAltaCtrl', function($scope, $http, $location) {

});
App.controller('proveedorListarCtrl', function($scope, $http) {

});




//CRUD para productos
App.controller('productoAltaCtrl', function($scope, $http, $location) {

});
App.controller('productoListarCtrl', function($scope, $http) {

});


// Movimientos


App.controller('registroMasivoCtrl', function($scope, $http) {
	

});*/


