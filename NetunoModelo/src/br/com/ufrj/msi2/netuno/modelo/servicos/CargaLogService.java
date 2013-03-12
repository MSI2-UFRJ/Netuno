package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.io.Serializable;

import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;
import br.com.ufrj.msi2.netuno.modelo.entidades.CargaLog;

@Local
public interface CargaLogService extends Serializable {

	public CargaLog recuperaUltimoCargaLogDeCarga(Carga carga);

}
