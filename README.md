# APIPrestamoBBVA
Implementación de Sistema de amortización Francés para simulación de Préstamos (FCQ)

BD ORACLE XE 11c:
 - Ingresar con el usuario sys/system -> http://127.0.0.1:8080/apex/f?p=4950:1:2153113276439876
   User: SYSTEM
   Pass: ******** 
 - Crear el Workspace y Schema(User/Password):
   Workspace: WSRETO
   User: DBRETO
   Pass: ********
 - Logearse con el schema creado -> http://127.0.0.1:8080/apex/f?p=4550:1:5741108137901748
   Ir a la opción/pestaña SQL Workshop
   Luego a la opción SQL Scripts
   Upload > Cargar los archivos database.sql y pa_iaa_bbva_pkc.sql ubicados en la ruta APIPrestamoBBVA/API_Prestamo/database/
   Con esta carga se crearan las tablas y procedimientos necesarios para que funcione la aplicación.

Server Tomcat:
 - Configurar el DS para la conexion a BD (DS_DBRETO)
 
Back-End:(Spring 4, Jersey RESTFul, Spring Velocity, JavaMail)
 - APIMail: Servicio para envio de email al cliente.
 - API_Prestamo: Logica para Cálculo de Amortización.
 
Front-End: (HTML5, BootStrap, AngularJS 1)
 - Cliente que consume API_Prestamo para calculo y envio de prestamo.
