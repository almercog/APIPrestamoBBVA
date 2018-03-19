<div class="modal-header">
    <h3>Simulador de Email</h3>
</div>
<form name="form.cliente" ng-submit="submitForm()" novalidate>
    <div class="modal-body">
        <!-- EMAIL -->
        <div class="form-group">
            <label>Email</label>
            <input type="email" name="email" class="form-control" ng-model="email" required>
            <p ng-show="form.cliente.email.$invalid && !form.cliente.email.$pristine" class="help-block">Ingrese un email valido.</p>
        </div>

    </div>
    <div class="modal-footer">
        <button type="submit" class="btn btn-primary" ng-disabled="form.cliente.$invalid">OK</button>
        <button class="btn btn-warning" ng-click="cancel()">Cancelar</button>
    </div>
</form>