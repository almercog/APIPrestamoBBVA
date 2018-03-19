CREATE OR REPLACE PACKAGE pq_iaa_bbva IS

  PROCEDURE sp_lst_parametro(a_idTipPar       IN  CFG_PARAMETRO.IDTIPPAR%type,
                             a_abreviatura IN  CFG_PARAMETRO.ABREVIATURA%type,
                             a_descripcion IN  CFG_PARAMETRO.DESCRIPCION%type,
                             a_cursor      OUT sys_refcursor);

  PROCEDURE sp_mnt_prestamo(a_idPrestamo   IN OUT MAE_PRESTAMO.IDPRESTAMO%type,
                            R_MAE_PRESTAMO MAE_PRESTAMO%rowtype);

  PROCEDURE sp_mnt_prestamo(a_idPrestamo       IN OUT MAE_PRESTAMO.IDPRESTAMO%type,
                            a_prestamo         IN MAE_PRESTAMO.PRESTAMO%type,
                            a_tasa             IN MAE_PRESTAMO.TASA%type,
                            a_plazo            IN MAE_PRESTAMO.PLAZO%type,
                            a_tipoPlazo        IN MAE_PRESTAMO.TIPOPLAZO%type,
                            a_fecDesembolso    IN MAE_PRESTAMO.FECDESEMBOLSO%type,
                            a_fecIniPrestamo   IN MAE_PRESTAMO.FECINIPRESTAMO%type,
                            a_fecFinPrestamo   IN MAE_PRESTAMO.FECFINPRESTAMO%type,
                            a_indPeriodoGracia IN MAE_PRESTAMO.INDPERIODOGRACIA%type,
                            a_periodoGracia    IN MAE_PRESTAMO.PERIODOGRACIA%type,
                            a_indCapInteres    IN MAE_PRESTAMO.INDCAPINTERES%type,
                            a_email            IN MAE_PRESTAMO.EMAIL%type,
                            a_url              IN MAE_PRESTAMO.URL%type,
                            a_origentrans      IN MAE_PRESTAMO.ORIGENTRANS%type,
                            a_usuario          IN DET_PRESTAMO.USUCREACION%type);

  PROCEDURE sp_mnt_prestamodet(a_idPrestamoDet   IN OUT DET_PRESTAMO.IDPRESTAMO%type,
                               R_DET_PRESTAMO    DET_PRESTAMO%rowtype);

  PROCEDURE sp_mnt_prestamodet(a_idPrestamoDet    IN OUT DET_PRESTAMO.IDPRESTAMODET%type,
                               a_idPrestamo       IN DET_PRESTAMO.IDPRESTAMO%type,
                               a_fecVencPagoCuota IN DET_PRESTAMO.FECVENCPAGOCUOTA%type,
                               a_periodo          IN DET_PRESTAMO.PERIODO%type,
                               a_cuota            IN DET_PRESTAMO.CUOTA%type,
                               a_interes          IN DET_PRESTAMO.INTERES%type,
                               a_amortizacion     IN DET_PRESTAMO.AMORTIZACION%type,
                               a_totalAmortizado  IN DET_PRESTAMO.TOTALAMORTIZADO%type,
                               a_capitalPendiente IN DET_PRESTAMO.CAPITALPENDIENTE%type,
                               a_usuario          IN DET_PRESTAMO.USUCREACION%type);

  PROCEDURE sp_get_prestamo(a_idPrestamo  IN  MAE_PRESTAMO.IDPRESTAMO%type,
                            a_cursor      OUT sys_refcursor);

  PROCEDURE sp_lst_prestamodet(a_idPrestamo  IN  DET_PRESTAMO.IDPRESTAMO%type,
                               a_cursor      OUT sys_refcursor);
END pq_iaa_bbva;
/
CREATE OR REPLACE PACKAGE BODY pq_iaa_bbva IS

  PROCEDURE sp_lst_parametro(a_idTipPar    IN  CFG_PARAMETRO.IDTIPPAR%type,
                             a_abreviatura IN  CFG_PARAMETRO.ABREVIATURA%type,
                             a_descripcion IN  CFG_PARAMETRO.DESCRIPCION%type,
                             a_cursor      OUT sys_refcursor) IS
  BEGIN
    OPEN a_cursor FOR
      SELECT idPar, idTipPar, codigo, codigoC, codigoN, abreviatura, descripcion, descripcion2, indActivo, usucreacion
        FROM CFG_PARAMETRO
       WHERE (IDTIPPAR = a_idTipPar OR a_idTipPar IS NULL)
         AND (ABREVIATURA = a_abreviatura OR a_abreviatura IS NULL)
         AND (DESCRIPCION = a_descripcion OR a_descripcion IS NULL);
    
  END sp_lst_parametro;

  PROCEDURE sp_mnt_prestamo(a_idPrestamo   IN OUT MAE_PRESTAMO.IDPRESTAMO%type,
                            R_MAE_PRESTAMO MAE_PRESTAMO%rowtype) IS
  BEGIN
    update MAE_PRESTAMO
       set PRESTAMO         = R_MAE_PRESTAMO.PRESTAMO,
           TASA             = R_MAE_PRESTAMO.TASA,
           PLAZO            = R_MAE_PRESTAMO.PLAZO,
		   TIPOPLAZO        = R_MAE_PRESTAMO.TIPOPLAZO,
           FECDESEMBOLSO    = R_MAE_PRESTAMO.FECDESEMBOLSO,
		   FECINIPRESTAMO   = R_MAE_PRESTAMO.FECINIPRESTAMO,
		   FECFINPRESTAMO   = R_MAE_PRESTAMO.FECFINPRESTAMO,
           INDPERIODOGRACIA = R_MAE_PRESTAMO.INDPERIODOGRACIA,
           PERIODOGRACIA    = R_MAE_PRESTAMO.PERIODOGRACIA,
           INDCAPINTERES    = R_MAE_PRESTAMO.INDCAPINTERES,
           EMAIL            = R_MAE_PRESTAMO.EMAIL,
           URL              = R_MAE_PRESTAMO.URL,
           ORIGENTRANS      = R_MAE_PRESTAMO.ORIGENTRANS,
           USUMODIF         = R_MAE_PRESTAMO.USUMODIF,
           FECMODIF         = sysdate
     where IDPRESTAMO       = a_idPrestamo;

    if sql%rowcount = 0 then
      Select SQ_MAE_PRESTAMO.nextval
        Into a_idPrestamo
        From Dual;
        
      insert into MAE_PRESTAMO
        (IDPRESTAMO,
         PRESTAMO,
         TASA,       
         PLAZO,
         TIPOPLAZO,		 
         FECDESEMBOLSO, 
		 FECINIPRESTAMO,
		 FECFINPRESTAMO,
         INDPERIODOGRACIA,
         PERIODOGRACIA,   
         INDCAPINTERES,   
         EMAIL,
         URL,		 
         ORIGENTRANS,   
         USUCREACION,     
         FECCREACION)
      values
        (a_idPrestamo,
         R_MAE_PRESTAMO.PRESTAMO,
         R_MAE_PRESTAMO.TASA,
         R_MAE_PRESTAMO.PLAZO,
         R_MAE_PRESTAMO.TIPOPLAZO,
         R_MAE_PRESTAMO.FECDESEMBOLSO,
		 R_MAE_PRESTAMO.FECINIPRESTAMO,
		 R_MAE_PRESTAMO.FECFINPRESTAMO,
         R_MAE_PRESTAMO.INDPERIODOGRACIA,
         R_MAE_PRESTAMO.PERIODOGRACIA,
         R_MAE_PRESTAMO.INDCAPINTERES,
         R_MAE_PRESTAMO.EMAIL,
         R_MAE_PRESTAMO.URL,
         R_MAE_PRESTAMO.ORIGENTRANS,
         R_MAE_PRESTAMO.USUCREACION,
         sysdate
         );
    end if;
  END sp_mnt_prestamo;

  PROCEDURE sp_mnt_prestamo(a_idPrestamo       IN OUT MAE_PRESTAMO.IDPRESTAMO%type,
                            a_prestamo         IN MAE_PRESTAMO.PRESTAMO%type,
                            a_tasa             IN MAE_PRESTAMO.TASA%type,
                            a_plazo            IN MAE_PRESTAMO.PLAZO%type,
                            a_tipoPlazo        IN MAE_PRESTAMO.TIPOPLAZO%type,
                            a_fecDesembolso    IN MAE_PRESTAMO.FECDESEMBOLSO%type,
                            a_fecIniPrestamo   IN MAE_PRESTAMO.FECINIPRESTAMO%type,
                            a_fecFinPrestamo   IN MAE_PRESTAMO.FECFINPRESTAMO%type,
                            a_indPeriodoGracia IN MAE_PRESTAMO.INDPERIODOGRACIA%type,
                            a_periodoGracia    IN MAE_PRESTAMO.PERIODOGRACIA%type,
                            a_indCapInteres    IN MAE_PRESTAMO.INDCAPINTERES%type,
                            a_email            IN MAE_PRESTAMO.EMAIL%type,
                            a_url              IN MAE_PRESTAMO.URL%type,
                            a_origentrans      IN MAE_PRESTAMO.ORIGENTRANS%type,
                            a_usuario          IN DET_PRESTAMO.USUCREACION%type) IS
    R_MAE_PRESTAMO MAE_PRESTAMO%rowtype;
  BEGIN    
    R_MAE_PRESTAMO.PRESTAMO := a_prestamo;
    R_MAE_PRESTAMO.TASA := a_tasa;   
    R_MAE_PRESTAMO.PLAZO := a_plazo;
	R_MAE_PRESTAMO.TIPOPLAZO := a_tipoPlazo;
    R_MAE_PRESTAMO.FECDESEMBOLSO := a_fecDesembolso;
	R_MAE_PRESTAMO.FECINIPRESTAMO := a_fecIniPrestamo;
	R_MAE_PRESTAMO.FECFINPRESTAMO := a_fecFinPrestamo;
    R_MAE_PRESTAMO.INDPERIODOGRACIA := a_indPeriodoGracia;
    R_MAE_PRESTAMO.PERIODOGRACIA := a_periodoGracia;  
    R_MAE_PRESTAMO.INDCAPINTERES := a_indCapInteres;
    R_MAE_PRESTAMO.EMAIL := a_email;
	R_MAE_PRESTAMO.URL := a_url;
    R_MAE_PRESTAMO.ORIGENTRANS := a_origentrans;
    R_MAE_PRESTAMO.USUCREACION := a_usuario;
    R_MAE_PRESTAMO.USUMODIF    := a_usuario;
    
    sp_mnt_prestamo(a_idPrestamo, R_MAE_PRESTAMO);
  END sp_mnt_prestamo;

  PROCEDURE sp_mnt_prestamodet(a_idPrestamoDet   IN OUT DET_PRESTAMO.IDPRESTAMO%type,
                               R_DET_PRESTAMO    DET_PRESTAMO%rowtype) IS
  BEGIN
    update DET_PRESTAMO
       set IDPRESTAMO       = R_DET_PRESTAMO.IDPRESTAMO,
           FECVENCPAGOCUOTA = R_DET_PRESTAMO.FECVENCPAGOCUOTA,
           PERIODO          = R_DET_PRESTAMO.PERIODO,
           CUOTA            = R_DET_PRESTAMO.CUOTA,
           INTERES          = R_DET_PRESTAMO.INTERES,
           AMORTIZACION     = R_DET_PRESTAMO.AMORTIZACION,
           TOTALAMORTIZADO  = R_DET_PRESTAMO.TOTALAMORTIZADO,
           CAPITALPENDIENTE = R_DET_PRESTAMO.CAPITALPENDIENTE,
           USUMODIF         = R_DET_PRESTAMO.USUMODIF,
           FECMODIF         = sysdate
     where IDPRESTAMODET    = a_idPrestamoDet;

    if sql%rowcount = 0 then
      Select SQ_DET_PRESTAMO.nextval
        Into a_idPrestamoDet
        From Dual;
        
      insert into DET_PRESTAMO
        (IDPRESTAMODET,
         IDPRESTAMO,      
         PERIODO,          
         CUOTA,  
         INTERES,
         AMORTIZACION,
         TOTALAMORTIZADO,
         CAPITALPENDIENTE,
         FECVENCPAGOCUOTA, 
         USUCREACION,     
         FECCREACION)
      values
        (a_idPrestamoDet,
         R_DET_PRESTAMO.IDPRESTAMO,
         R_DET_PRESTAMO.PERIODO,
         R_DET_PRESTAMO.CUOTA,
         R_DET_PRESTAMO.INTERES,
         R_DET_PRESTAMO.AMORTIZACION,
         R_DET_PRESTAMO.TOTALAMORTIZADO,
         R_DET_PRESTAMO.CAPITALPENDIENTE,
         R_DET_PRESTAMO.FECVENCPAGOCUOTA,
         R_DET_PRESTAMO.USUCREACION,
         sysdate
         );
    end if;
    
  END sp_mnt_prestamodet;

  PROCEDURE sp_mnt_prestamodet(a_idPrestamoDet    IN OUT DET_PRESTAMO.IDPRESTAMODET%type,
                               a_idPrestamo       IN DET_PRESTAMO.IDPRESTAMO%type,
                               a_fecVencPagoCuota IN DET_PRESTAMO.FECVENCPAGOCUOTA%type,
                               a_periodo          IN DET_PRESTAMO.PERIODO%type,
                               a_cuota            IN DET_PRESTAMO.CUOTA%type,
                               a_interes          IN DET_PRESTAMO.INTERES%type,
                               a_amortizacion     IN DET_PRESTAMO.AMORTIZACION%type,
                               a_totalAmortizado  IN DET_PRESTAMO.TOTALAMORTIZADO%type,
                               a_capitalPendiente IN DET_PRESTAMO.CAPITALPENDIENTE%type,
                               a_usuario          IN DET_PRESTAMO.USUCREACION%type) IS
    R_DET_PRESTAMO DET_PRESTAMO%rowtype;
  BEGIN
    R_DET_PRESTAMO.IDPRESTAMO := a_idPrestamo;
    R_DET_PRESTAMO.FECVENCPAGOCUOTA := a_fecVencPagoCuota;
    R_DET_PRESTAMO.PERIODO := a_periodo;
    R_DET_PRESTAMO.CUOTA := a_cuota;
    R_DET_PRESTAMO.INTERES := a_interes;
    R_DET_PRESTAMO.AMORTIZACION := a_amortizacion;
    R_DET_PRESTAMO.TOTALAMORTIZADO := a_totalAmortizado;
    R_DET_PRESTAMO.CAPITALPENDIENTE := a_capitalPendiente;
    R_DET_PRESTAMO.USUCREACION := a_usuario;
    R_DET_PRESTAMO.USUMODIF := a_usuario;
    
    sp_mnt_prestamodet(a_idPrestamoDet, R_DET_PRESTAMO);
    
  END sp_mnt_prestamodet;

  PROCEDURE sp_get_prestamo(a_idPrestamo  IN  MAE_PRESTAMO.IDPRESTAMO%type,
                            a_cursor      OUT sys_refcursor) IS
  BEGIN
    OPEN a_cursor FOR
       SELECT * 
         FROM MAE_PRESTAMO
        WHERE IDPRESTAMO = a_idPrestamo;
    
  END sp_get_prestamo;

  PROCEDURE sp_lst_prestamodet(a_idPrestamo  IN  DET_PRESTAMO.IDPRESTAMO%type,
                               a_cursor      OUT sys_refcursor) IS
  BEGIN
    OPEN a_cursor FOR
       SELECT *
         FROM DET_PRESTAMO
        WHERE IDPRESTAMO = a_idPrestamo;
    
  END sp_lst_prestamodet;
END pq_iaa_bbva;
​