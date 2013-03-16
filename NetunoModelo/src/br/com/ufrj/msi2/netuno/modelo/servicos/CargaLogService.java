package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.io.Serializable;

import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;
import br.com.ufrj.msi2.netuno.modelo.entidades.CargaLog;

@Local
public interface CargaLogService extends Serializable {

	public CargaLog criarCargaLog();
	
	public void salvarCargaLog(CargaLog cargaLog);
	
	/**
	 * Recupera CargaLog mais recente de uma determinada Carga.
	 * @param carga
	 * @return CargaLog mais recente ou null, caso não exista CargaLog.
	 */
	public CargaLog recuperaUltimoCargaLogDeCarga(Carga carga);

}
