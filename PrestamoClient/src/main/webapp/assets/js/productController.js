'use strict';
 
angular.module('PrestamoApp').controller('PrestamoController', ['$scope', 'PrestamoService', function($scope, PermisoService) {
    var self = this;
    self.prestamo = { idPrestamo: null, prestamo:0.0, tasa: 0.0 , plazo: 0, tipoPlazo: 'M', fecDesembolso: new Date(), indPeriodoGracia: 'N', 
    		          numPeriodoGracia: 0, indCapitalInteres: 'N', origen:'APIRETO', email: ''};
    self.lstPrestamoDet = [];
    self.codMensaje = '';
    self.dscMensaje = '';
 
    self.submit = submit;
    self.sendEmail = sendEmail;
    self.reset = reset;
 
    function sendEmail() {
    	if (self.prestamo.idPrestamo===null) {
    		alert("No ha generado prestamos");
    	}
    	PermisoService.sendEmail({idPrestamo: self.prestamo.idPrestamo, email: self.prestamo.email})
            .then(
            function(d) {
                self.codMensaje = d.codMensaje;
                self.dscMensaje = d.dscMensaje;
                console.error('Email enviado al cliente...');
            },
            function(errResponse){
                console.error('Error while fetching lstPrestamoDet');
            }
        );
    }
 
    function createPrestamo(prestamo) {
    	PermisoService.createPrestamo(prestamo)
            .then(
            function(d) {
            	if (d.prestamo) {
                	self.prestamo.idPrestamo = d.prestamo.idPrestamo;
                	self.lstPrestamoDet = d.lstPrestamoDet;
            	}
                self.codMensaje = d.codMensaje;
                self.dscMensaje = d.dscMensaje;
            },
            function(errResponse){
                console.error('Error while creating prestamo');
            }
        );
    }
 
    function updatePrestamo(prestamo) {
    	PermisoService.updatePrestamo(prestamo)
            .then(
            function(d) {
            	self.lstPrestamoDet = d.lstPrestamoDet;
            },
            function(errResponse) {
                console.error('Error while updating prestamo');
            }
        );
    }
  
    function submit() {
        if (self.prestamo.idPrestamo===null) {
            console.log('Saving New Prestamo', self.prestamo);
            createPrestamo(self.prestamo);
        }else{
            updatePrestamo(self.prestamo);
            console.log('Prestamo updated with idPrestamo ', self.prestamo.idPrestamo);
        }
        // reset();
    }
  
    function reset() {
        self.prestamo={idPrestamo: null, prestamo:0.0, tasa: 0.0 , plazo: 0, tipoPlazo: 'M', fecDesembolso: new Date(), indPeriodoGracia: 'N', 
		          numPeriodoGracia: 0, indCapitalInteres: 'N', origen:'APIRETO', email: ''};
        self.lstPrestamoDet = [];
        self.codMensaje = '';
        self.dscMensaje = '';
        $scope.myForm.$setPristine();
    }
 
}]);