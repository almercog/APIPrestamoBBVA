<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Sistema de amortización Francés para simulación de Préstamos</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<link href="<c:url value='/assets/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-app="PrestamoApp" class="ng-cloak">
  
      <div class="generic-container" ng-controller="PrestamoController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">Simulador de prestamo</span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                   <input type="hidden" ng-model="ctrl.prestamo.idPrestamo" />
                       
                    <div class="row">
                          <div class="form-group col-md-12">
					          <div ng-show="ctrl.codMensaje" class="{{ctrl.codMensaje==1?'has-success':'has-error'}}">
					              {{ctrl.dscMensaje}}
					          </div>
                          </div>
                     </div>
                       
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="prestamo">Importe</label>
                              <div class="col-md-7">
                                  <input type="number" ng-model="ctrl.prestamo.prestamo" id="prestamo" format="currency" min="1" class="field form-control input-sm" placeholder="Ingrese prestamo"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.price.$error.required">Es un campo requerido</span>
                                      <span ng-show="myForm.price.$error.number">No es un numero valido</span>
                                  </div>
                              </div>
                          </div>
                      </div>                
                       
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="tasa">Tasa (%)</label>
                              <div class="col-md-7">
                                  <input type="number" ng-model="ctrl.prestamo.tasa" id="tasa" min="1" max="100" class="field form-control input-sm" placeholder="Ingrese Tipo de interés"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.price.$error.required">Es un campo requerido</span>
                                      <span ng-show="myForm.price.$error.number">No es un numero valido</span>
                                  </div>
                              </div>
                          </div>
                      </div>                
                       
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="plazo">Plazo de Amortización</label>
                              <div class="col-md-7">
                                  <input type="number" ng-model="ctrl.prestamo.plazo" id="plazo" format="currency" min="1" class="field form-control input-sm" placeholder="Ingrese amortizacion"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.price.$error.required">Es un campo requerido</span>
                                      <span ng-show="myForm.price.$error.number">No es un numero valido</span>
                                  </div>
                              </div>
                          </div>
                      </div>
                       
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="tipoPlazo">Tipo de Plazo</label>
                              <div class="col-md-7">
                                  <input type="radio" ng-model="ctrl.prestamo.tipoPlazo" id="tipoPlazo" value="M"> Meses
                                  <input type="radio" ng-model="ctrl.prestamo.tipoPlazo" id="tipoPlazo1" value="A"> Años
                              </div>
                          </div>
                      </div>
                       
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="fecDesembolso">Fecha de Desembolso</label>
                              <div class="col-md-7">
                                  <input type="date" ng-model="ctrl.prestamo.fecDesembolso" id="fecDesembolso" class="field form-control input-sm" placeholder="Ingrese amortizacion"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.price.$error.required">Es un campo requerido</span>
                                      <span ng-show="myForm.price.$error.number">No es un numero valido</span>
                                  </div>
                              </div>
                          </div>
                      </div>
                       
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="indPeriodoGracia">Con Periodo Gracia</label>
                              <div class="col-md-7">
                                  <input type="radio" ng-model="ctrl.prestamo.indPeriodoGracia" id="indPeriodoGracia" value="S"> SI
                                  <input type="radio" ng-model="ctrl.prestamo.indPeriodoGracia" id="indPeriodoGracia1" value="N"> NO
                              </div>
                          </div>
                      </div>
                       
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="numPeriodoGracia">Periodo de Gracia</label>
                              <div class="col-md-7">
                                  <input type="number" ng-model="ctrl.prestamo.numPeriodoGracia" id="numPeriodoGracia" ng-disabled="{{ctrl.prestamo.indPeriodoGracia.value=='N'}}" class="field form-control input-sm" placeholder="Ingrese numPeriodoGracia"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.price.$error.required">Es un campo requerido</span>
                                      <span ng-show="myForm.price.$error.number">No es un numero valido</span>
                                  </div>
                              </div>
                          </div>
                      </div>
                       
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="email">Email</label>
                              <div class="col-md-7">
                                  <input type="email" ng-model="ctrl.prestamo.email" id="email" class="field form-control input-sm" placeholder="Ingrese email"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.price.$error.required">Es un campo requerido</span>
                                      <span ng-show="myForm.price.$error.number">No es un numero valido</span>
                                  </div>
                              </div>
                          </div>
                      </div>
 
                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit" value="Calcular" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <input type="button" ng-click="ctrl.sendEmail()" value="Enviar EMail" class="btn btn-success btn-sm" ng-disabled="{{ctrl.prestamo.email}}">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                          </div>
                      </div>
                  </form>
              </div>
          </div>
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">Detalle de Prestamos</span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>Numero de Periodo</th>
                              <th>Interes</th>
                              <th>Amortizacion</th>
                              <th>Cuota de Pago</th>
                              <th>Total Amortizado</th>
                              <th>Capital Pendiente</th>
                              <!--<th>Fecha Venc. Cuota</th>-->
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="p in ctrl.lstPrestamoDet">
                              <td><span ng-bind="p.periodo"></span></td>
                              <td><span ng-bind="p.interes"></span></td>
                              <td><span ng-bind="p.amortizacion"></span></td>
                              <td><span ng-bind="p.cuota"></span></td>
                              <td><span ng-bind="p.totalAmortizado"></span></td>
                              <td><span ng-bind="p.capitalPendiente"></span></td>
                              <!--<td><span ng-bind="p.fecVencPagoCuota"></span></td>-->
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.js"></script>
	<script src="<c:url value='/assets/js/app.js' />"></script>
	<script src="<c:url value='/assets/js/productService.js' />"></script>
	<script src="<c:url value='/assets/js/productController.js' />"></script>
</body>
</html>
