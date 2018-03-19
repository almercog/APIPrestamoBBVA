'use strict';
 
angular.module('PrestamoApp').factory('PrestamoService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:9090/API_Prestamo/';
 
    var factory = {
    	sendEmail: sendEmail,
        createPrestamo: createPrestamo,
        updatePrestamo: updatePrestamo
    };
 
    return factory;
 
    function sendEmail(emailClient) {
    	console.log(emailClient);
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI+'api/email', emailClient)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching email');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function createPrestamo(prestamo) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI+'api/prestamos', prestamo)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating prestamo');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function updatePrestamo(prestamo) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+'api/prestamos', prestamo)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse) {
                console.error('Error while updating prestamo');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
}]);